package main;

import java.awt.GridBagConstraints;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import Piece.King;
import Piece.Piece;
//import javafx.geometry.Pos;
import object.Board;
import object.Position;

public class MainGame {

	public boolean color = true;
    public Board gameBoard = null;
    
    boolean blackKingChecked = false, whiteKingChecked = false;

    //make it easier for checkmate
    King blackKing = null, whiteKing = null;
    int oldR = 0, oldC = 0;
    Piece chosenPiece = null;
    Piece checkKing = null;
    Position[] avalPoss = null;
    
    
    public MainGame deepCopy(){
    	MainGame prevGame = new MainGame();
    	prevGame.color = this.color;
    	prevGame.gameBoard = this.gameBoard.deepCopy();
    	prevGame.whiteKingChecked = this.whiteKingChecked;
    	prevGame.blackKingChecked = this.blackKingChecked;
    	prevGame.whiteKing = (King) prevGame.gameBoard.grids[this.whiteKing.position.row][this.whiteKing.position.col];
    	prevGame.blackKing = (King) prevGame.gameBoard.grids[this.blackKing.position.row][this.blackKing.position.col];
    	return prevGame;
    }
    
    public MainGame() {
    	gameBoard = new Board();
    	blackKing = (King) gameBoard.grids[7][4];//shallow copy like a pointer
    	whiteKing = (King) gameBoard.grids[0][4];
    	//printBoard();

	}
  
    
    /*
    public MainGame () {

        // Set the initial board
    	blackKing = (King) gameBoard.grids[7][4];//shallow copy like a pointer
        whiteKing = (King) gameBoard.grids[0][4];
        
        
        System.out.println("Game start!");
        while (true) {
            King myKing = null;
            if(color)
                myKing = whiteKing;
            else
                myKing = blackKing;
            if(Stalemate(gameBoard, myKing))
            {
                System.out.println("Stalemate");
                return;
            }
            boolean whiteKingFound = false, blackKingFound = false;


            //print the board for test use and easy ui
            for(int i =7; i >= 0 ; i--)
            {

                for(int j = 0; j < 8; j++)
                {
                    if(gameBoard.grids[i][j] == null)
                        System.out.print(" - ");
                    else if(gameBoard.grids[i][j].name.equals("Pawn"))
                    {
                        if(gameBoard.grids[i][j].color == true)
                            System.out.print(" P ");
                        else
                            System.out.print(" p ");
                    }
                    else if(gameBoard.grids[i][j].name.equals("Bishop"))
                    {
                        if(gameBoard.grids[i][j].color == true)
                            System.out.print(" B ");
                        else
                            System.out.print(" b ");

                    }
                    else if(gameBoard.grids[i][j].name.equals("King"))
                    {
                        if(gameBoard.grids[i][j].color == true) {
                            System.out.print(" K ");
                            whiteKingFound = true;
                        }
                        else {
                            System.out.print(" k ");
                            blackKingFound = true;
                        }

                    }
                    else if(gameBoard.grids[i][j].name.equals("Knight"))
                    {
                        if(gameBoard.grids[i][j].color == true)
                            System.out.print(" N ");
                        else
                            System.out.print(" n ");

                    }
                    else if(gameBoard.grids[i][j].name.equals("Queen"))
                    {
                        if(gameBoard.grids[i][j].color == true)
                            System.out.print(" Q ");
                        else
                            System.out.print(" q ");

                    }
                    else if(gameBoard.grids[i][j].name.equals("Rook"))
                    {
                        if(gameBoard.grids[i][j].color == true)
                            System.out.print(" R ");
                        else
                            System.out.print(" r ");

                    }

                }
                System.out.println("  ");
            }

            if(!whiteKingFound && blackKingFound) {
                System.out.println("Black win!");
                return;
            }
            else if(whiteKingFound && !blackKingFound) {
                System.out.println("White win!");
                return;
            }
            if(!whiteKingFound && !blackKingFound) {
                System.out.println("What happen QAQ!");
                return;
            }
            int r = 0, c = 0;
            //get user input position, pos of the piece he chooses
            if (color == true) {
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("White Player, Please enter the row number: ");
                r = reader.nextInt(); // Scans the next token of the input as an int.

                System.out.println("White Player, Please enter the col number: ");
                c = reader.nextInt(); // Scans the next token of the input as an int.
            }
            if (color == false) {
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Black Player, Please enter the row number: ");
                r = reader.nextInt(); // Scans the next token of the input as an int.

                System.out.println("Black Player, Please enter the col number: ");
                c = reader.nextInt(); // Scans the next token of the input as an int.
            }
            //check if the input position is valid, and if there is a piece on that pos

            Position posChosen = new Position(r, c);
            Boolean chosenAval = gameBoard.ifchosenPosAval(posChosen, color);

            if(!chosenAval)
            {
                System.out.println("This Postion is not available! Try again");
                continue;//??????
            }

            //variables need when chosenAval is true
            int destR = 0, destC = 0;
            Piece chosenPiece = null;
            Piece checkKing = null;
            Position[] avalPoss = null;

            if (chosenAval) {
                //use allmoves function to check the validity of the move ordered by user
                chosenPiece = gameBoard.grids[r][c];
//if (gameBoard != null) System.out.println("Not null");

                //Check All aval position (r,c) can goto
                avalPoss = chosenPiece.allmoves(gameBoard);
                System.out.println("available position");
                for(Position p : avalPoss)
                {
                    System.out.println("(" + p.row + ", " + p.col + "); ");
                }
                //get user input, this time is the pos where he wants the piece to go
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Player, Please enter the destination row number: ");
                destR = reader.nextInt(); // Scans the next token of the input as an int.
                System.out.println("Player, Please enter the destination col number: ");
                destC = reader.nextInt();

                //If user give a good destination position, which is in allmoves return value, then we move

                Position posChosenDest = new Position(destR, destC);
                boolean DestOK = false;
                for (Position a : avalPoss) {
                        //move the piece
                        if(a.row != posChosenDest.row || a.col != posChosenDest.col)
                            continue;//not available destination try next

                        DestOK = true;
                        chosenPiece.move(destR, destC);
                        //set old destination = null
                        gameBoard.grids[destR][destC] = gameBoard.grids[r][c];
                        gameBoard.grids[r][c] = null;
                        break;
                }
                if(!DestOK) {
                    System.out.println("Not valid destination position!");
                    continue;
                }

                //set enemyking
                King enemyKing = null;
                if(color) {
                    enemyKing = whiteKing;
                }
                else {
                    enemyKing = blackKing;
                }
                boolean chekced = Check(gameBoard, enemyKing);

                //we only need to check CHECKMATE when we check the king in the previous part
                if (chekced) {
                    System.out.println("Check!");
                    boolean nowCheckmate = false;
                    //call Check mate function
                    if(color)//white turn so black king in check
                    {
                         nowCheckmate = CheckMate(gameBoard, blackKing);
                    }
                    else
                    {
                        nowCheckmate = CheckMate(gameBoard, whiteKing);
                    }
                    if(nowCheckmate)
                    {
                        System.out.print("game over you win");
                        return;
                    }
                }


            }
            color = !color;
            System.out.println("Now is the turn of the other Player!");
                    //else {
                    //    System.out.println("The destination position is not available. Try again!");
                    //   continue;//if destination invalid, continue the loop to get a new piece to move
                    //}
        }


    }
    
*/
    public void printBoard()
    {
    	//print the board for test use and easy ui
        for(int i =7; i >= 0 ; i--)
        {

            for(int j = 0; j < 8; j++)
            {
                if(gameBoard.grids[i][j] == null)
                    System.out.print(" - ");
                else if(gameBoard.grids[i][j].name.equals("Pawn"))
                {
                    if(gameBoard.grids[i][j].color == true)
                        System.out.print(" P ");
                    else
                        System.out.print(" p ");
                }
                else if(gameBoard.grids[i][j].name.equals("Bishop"))
                {
                    if(gameBoard.grids[i][j].color == true)
                        System.out.print(" B ");
                    else
                        System.out.print(" b ");

                }
                else if(gameBoard.grids[i][j].name.equals("King"))
                {
                    if(gameBoard.grids[i][j].color == true) {
                        System.out.print(" K ");
                    }
                    else {
                        System.out.print(" k ");
                    }

                }
                else if(gameBoard.grids[i][j].name.equals("Knight"))
                {
                    if(gameBoard.grids[i][j].color == true)
                        System.out.print(" N ");
                    else
                        System.out.print(" n ");

                }
                else if(gameBoard.grids[i][j].name.equals("Queen"))
                {
                    if(gameBoard.grids[i][j].color == true)
                        System.out.print(" Q ");
                    else
                        System.out.print(" q ");

                }
                else if(gameBoard.grids[i][j].name.equals("Rook"))
                {
                    if(gameBoard.grids[i][j].color == true)
                        System.out.print(" R ");
                    else
                        System.out.print(" r ");

                }

            }
            System.out.println("  ");
        }
    }
    
    public int blackUserTarget(int row, int line)
    {
        Position posChosenDest = new Position(row, line);
       
        for (Position a : avalPoss) {
                //move the piece
                if(a.row == posChosenDest.row && a.col == posChosenDest.col)
                {
                chosenPiece.move(row, line);
                //set old destination = null
                Piece temp = gameBoard.grids[row][line];
                gameBoard.grids[row][line] = gameBoard.grids[oldR][oldC];
                gameBoard.grids[oldR][oldC] = null;
                //if black king is checked then we have to solve this
                if(blackKingChecked)
                {
                	//if still checked
                	if(Check(gameBoard, blackKing))
                	{
                		//move pieces back
                		gameBoard.grids[oldR][oldC] = gameBoard.grids[row][line];
                		gameBoard.grids[row][line] = temp;
                		chosenPiece.move(oldR, oldC);
                		System.out.println("Save your king mother fucker");
                		return 0;
                	}
                	blackKingChecked = false;
                }
                
                boolean chekced = Check(gameBoard, whiteKing);

                //we only need to check CHECKMATE when we check the king in the previous part
                if (chekced) {
                    
                    boolean nowCheckmate = false;
                    //call Check mate function
                    
                   
                         nowCheckmate = CheckMate(gameBoard, whiteKing);
                 
                    if(nowCheckmate)
                    {
                    	printBoard();
                    	System.out.println("Game over and black win!");
                    	color = true;
                        return 3;//Check and Checkmate
                       
                    }
                    else{
                    	
                    	printBoard();
                    	System.out.println("Check!");
                    	whiteKingChecked = true;
                    	System.out.println("White player's turn!");
                    	color = true;
                    	return 2;//Check, not Checkmate
                    }
                }
                
                printBoard();
                System.out.println("White player's turn!");
                color = true;
                return 1; //Move successfule, no check
                }
        }
    	return 0;//invalid move
    }
    
    public int whiteUserTarget(int row, int line)
    {
    	
        Position posChosenDest = new Position(row, line);
        
        for (Position a : avalPoss) {
                //move the piece
                if(a.row == posChosenDest.row && a.col == posChosenDest.col)
                {
                chosenPiece.move(row, line);
                //set old destination = null
                Piece temp = gameBoard.grids[row][line];
                gameBoard.grids[row][line] = gameBoard.grids[oldR][oldC];
                gameBoard.grids[oldR][oldC] = null;
                //if white king is checked then we have to solve this
                if(whiteKingChecked)
                {
                	//if still checked
                	if(Check(gameBoard, whiteKing))
                	{
                		//move pieces back
                		gameBoard.grids[oldR][oldC] = gameBoard.grids[row][line];
                		gameBoard.grids[row][line] = temp;
                		chosenPiece.move(oldR, oldC);
                		System.out.println("Save your king mother fucker");
                		return 0;
                	}
                	whiteKingChecked = false;
                }
                
                boolean chekced = Check(gameBoard, blackKing);

                //we only need to check CHECKMATE when we check the king in the previous part
                if (chekced) {
                    
                    boolean nowCheckmate = false;
                    //call Check mate function
                    
                   
                         nowCheckmate = CheckMate(gameBoard, blackKing);
                 
                    if(nowCheckmate)
                    {
                    	System.out.println("Game over white player win!");
                    	printBoard();
                    	color = false;
                        return 3;//Check and Checkmate
                       
                    }
                    else{
                    	
                    	printBoard();
                    	System.out.println("Check!");
                    	blackKingChecked = true;
                    	System.out.println("Black player's turn!");
                    	color = false;
                    	return 2;//Check, not Checkmate
                    }
                }
                
                printBoard();
                System.out.println("Black player's turn!");
                color = false;
                return 1; //Move successfule, no check
                }
        }
    	return 0;//invalid move
    	
    }
    
    //get white user choose piece input and return all positions can go
    public Position[] whiteUserChoosePiece(int row, int line)
    {
    	//if chosen is not white piece, return null
    	Boolean chosenAval = gameBoard.ifchosenPosAval(new Position(row, line), true);

        if(!chosenAval)
        {
            return null;
        }

        //variables need when chosenAval is true
        if (chosenAval) {
        	System.out.println(row + ", " + line);
        	oldR = row;
        	oldC = line;
            //use allmoves function to check the validity of the move ordered by user
            chosenPiece = gameBoard.grids[row][line];
            //if (gameBoard != null) System.out.println("Not null");
            //Check All aval position (r,c) can goto
            avalPoss = chosenPiece.allmoves(gameBoard);
            System.out.println("available position");
            
            for(Position p : avalPoss)
            {
                System.out.println("(" + p.row + ", " + p.col + "); ");
            }
            
            return avalPoss;
        }
        
    	return null;
    }
    
    public Position[] blackUserChoosePiece(int row, int line)
    {
    	//if chosen is not white piece, return null
    	Boolean chosenAval = gameBoard.ifchosenPosAval(new Position(row, line), false);

        if(!chosenAval)
        {
            return null;
        }

        //variables need when chosenAval is true
        if (chosenAval) {
        	System.out.println(row + ", " + line);
        	oldR = row;
        	oldC = line;
            //use allmoves function to check the validity of the move ordered by user
            chosenPiece = gameBoard.grids[row][line];
            //if (gameBoard != null) System.out.println("Not null");
            //Check All aval position (r,c) can goto
            avalPoss = chosenPiece.allmoves(gameBoard);
            System.out.println("available position");
            for(Position p : avalPoss)
            {
                System.out.println("(" + p.row + ", " + p.col + "); ");
            }
            
            return avalPoss;
        }
    	return null;
    }
    
    //to see if enemy king is under attack of piece from us
    static boolean Check(Board thisboard, King enemyKing)
    {
    	if(enemyKing==null)
    		System.out.println("noking");
    	
        boolean enemyColor = enemyKing.color;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (thisboard.grids[i][j] == null || thisboard.grids[i][j].color == enemyColor)
                    continue;//continue if not the piece of player
                else
                {
                    Piece myPiece = thisboard.grids[i][j];
                    Position[] myPieceAllmoves = myPiece.allmoves(thisboard);
                    for(Position pos : myPieceAllmoves)
                    {
                        if(pos.row == enemyKing.position.row && pos.col == enemyKing.position.col)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean Stalemate(Board thisboard, King myKing){
        boolean myColor = myKing.color;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                //if what in the grid is empty or enemy piece, just move on
                if(thisboard.grids[i][j] == null || thisboard.grids[i][j].color != myColor)
                    continue;
                    //else consider all possible way it can moves
                else
                {
                    //First do a deep copy of the board!
                    Board tempboard = thisboard.deepCopy();
                    Piece tempMyPiece = tempboard.grids[i][j];
                    Position[] myPieceAllmoves = tempMyPiece.allmoves(tempboard);
                    for(Position pos : myPieceAllmoves)
                    {
                        //move the piece
                        tempMyPiece.move(pos.row, pos.col);
                        tempboard.grids[pos.row][pos.col] = tempboard.grids[i][j];
                        tempboard.grids[i][j] = null;
                        //see if my king is not checked, if it is not under check then return false
                        if(Check(tempboard, myKing))
                            continue;
                        else
                            return false;
                    }
                }

            }
        }
        return true;
    }

    //Checkmate functions, to see if the enemy king is checked in all situations
     static boolean CheckMate(Board thisboard, King enemyKing) {
    	// if(true)
    		// return false;
         boolean enemyColor = enemyKing.color;
        //start from now we will see if the king is totally done in all situations
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                //if what in the grid is empty or friendly piece, just move on
                if(thisboard.grids[i][j] == null || thisboard.grids[i][j].color != enemyColor)
                    continue;
                //else consider all possible way it can moves
                else
                {
                    //First do a deep copy of the board!
                    Board tempboard = thisboard.deepCopy();
                    Piece tempEnemyPiece = tempboard.grids[i][j];
                    Position[] enemyPieceAllmoves = tempEnemyPiece.allmoves(tempboard);
                    for(Position pos : enemyPieceAllmoves)
                    {
                        //move the piece
                        tempEnemyPiece.move(pos.row, pos.col);
                        tempboard.grids[pos.row][pos.col] = tempboard.grids[i][j];
                        tempboard.grids[i][j] = null;
                        //see if it is still check
                        if(tempEnemyPiece.name.equals("King"))
                        {
                            //if the enemy king is moved
                            if(!Check(tempboard, (King)tempboard.grids[pos.row][pos.col]))
                            {
                                //as long as there is a situation not checked, then return false
                                return false;
                            }
                            
                        }
                        else 
                        {
                        	if(!Check(tempboard, (King)tempboard.grids[enemyKing.position.row][enemyKing.position.col]))
                        	{
	                            //as long as there is a situation not checked, then return false
	                            return false;
                        	}
                        }
                    }
                }

            }
        }

        return true;
    }

}
/*
 * //check if CHECKMATE(Part)
							if(color)//now it's true->white so we need to find black king
							{
								Vector<Position> kingResult = null;
								//Vector<Position> whiteResult = new Vector<Position>();
								Position [] all = blackKing.allmoves(gameBoard);
								kingResult = new Vector<Position>(Arrays.asList(all));		
								
								for (int i = 0; i < 8; i ++)
								{
									for(int j = 0; j < 8; j++)
									{
										if(gameBoard.grids[i][j] == null || gameBoard.grids[i][j].color != color)
											continue;
										Piece myPiece = gameBoard.grids[i][j];
										Position[] myPieceAllmoves = myPiece.allmoves(gameBoard);
										for(Position myMove :myPieceAllmoves)
										{
											for(Position kingPos : kingResult)
											{
												if(kingPos.col == myMove.col && kingPos.row == myMove.row)
												{
													kingResult.remove(kingPos);
													continue;
												}
											}
											if(kingResult.size() == 0)
											{
												//if the king is killed, end the game
												System.out.println(" Check!!!!!!!!! ");
												
											}
										}
									}
									
								}
								
							}
							else// now it's false->black so we need to find white king
							{
								
							}
 * */
