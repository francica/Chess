package object;

import java.awt.Color;
import Piece.*;


public class Board {
	public Piece[][] grids;
	
	//constructor
	public Board()
	{
		//declare the board
		grids =  new Piece[8][8];
		
		//declare an empty board
		for(int i = 0; i < 8 ; i++)
		{
			for (int j = 0; j <  8; j ++)
			{
				grids[i][j] = null;
			}
		}
		
		//put pieces on the board ++++++++++++++++++++++++++++++
		//white pieces (as white)
		grids[0][0] = new Rook(0, 0, true);//车 true is white;
		grids[0][1] = new Knight(0, 1, true);//马
		grids[0][2] = new Bishop(0, 2, true);//象斜着走
		grids[0][3] = new Queen(0, 3, true);//车+象
		grids[0][4] = new King(0, 4, true);//四周一格
		grids[0][5] = new Bishop(0, 5, true);//象斜着走
		grids[0][6] = new Knight(0, 6, true);//马
		grids[0][7] = new Rook(0, 7, true);//车 true is white;
		
		grids[1][0] = new Pawn(1, 0, true);//兵 true is white;
		grids[1][1] = new Pawn(1, 1, true);//兵 true is white;
		grids[1][2] = new Pawn(1, 2, true);//兵 true is white;
		grids[1][3] = new Pawn(1, 3, true);//兵 true is white;
		grids[1][4] = new Pawn(1, 4, true);//兵 true is white;
		grids[1][5] = new Pawn(1, 5, true);//兵 true is white;
		grids[1][6] = new Pawn(1, 6, true);//兵 true is white;
		grids[1][7] = new Pawn(1, 7, true);//兵 true is white;
		
		//black pieces (as black)
		grids[7][0] = new Rook(7, 0, false);//车 false is black
		grids[7][1] = new Knight(7, 1, false);//马
		grids[7][2] = new Bishop(7, 2, false);//象斜着走
		grids[7][3] = new Queen(7, 3, false);//车+象
		grids[7][4] = new King(7, 4, false);//四周一格
		grids[7][5] = new Bishop(7, 5, false);//象斜着走
		grids[7][6] = new Knight(7, 6, false);//马
		grids[7][7] = new Rook(7, 7, false);//车 true is white;
		
		grids[6][0] = new Pawn(6, 0, false);//兵 black
		grids[6][1] = new Pawn(6, 1, false);//兵 black
		grids[6][2] = new Pawn(6, 2, false);//兵 black
		grids[6][3] = new Pawn(6, 3, false);//兵 black
		grids[6][4] = new Pawn(6, 4, false);//兵 black
		grids[6][5] = new Pawn(6, 5, false);//兵 black
		grids[6][6] = new Pawn(6, 6, false);//兵 black
		grids[6][7] = new Pawn(6, 7, false);//兵 black
		

		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++
	}

	//make a deep copy
	public Board deepCopy()
	{
		Board copyedOne = new Board();
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(this.grids[i][j] == null)
				{
					copyedOne.grids[i][j] = null;
				}
				else
				{
					Piece originalPiece = grids[i][j];
					if(originalPiece.name.equals("Bishop"))
					{
						copyedOne.grids[i][j] = new Bishop(originalPiece.position.row, originalPiece.position.col,
								originalPiece.color);
					}
					else if(originalPiece.name.equals("King"))
					{
						copyedOne.grids[i][j] = new King(originalPiece.position.row, originalPiece.position.col,
								originalPiece.color);
					}
					else if(originalPiece.name.equals("Knight"))
					{
						copyedOne.grids[i][j] = new Knight(originalPiece.position.row, originalPiece.position.col,
								originalPiece.color);
					}
					else if(originalPiece.name.equals("Pawn"))
					{
						copyedOne.grids[i][j] = new Pawn(originalPiece.position.row, originalPiece.position.col,
								originalPiece.color);
					}
					else if(originalPiece.name.equals("Queen"))
					{
						copyedOne.grids[i][j] = new Queen(originalPiece.position.row, originalPiece.position.col,
								originalPiece.color);
					}
					else if(originalPiece.name.equals("Rook"))
					{
						copyedOne.grids[i][j] = new Rook(originalPiece.position.row, originalPiece.position.col,
								originalPiece.color);
					}
				}
			}
		}
		return copyedOne;
	}
	
	
	public boolean ifchosenPosAval(Position p, boolean color)
	{
		if(grids[p.row][p.col] == null || grids[p.row][p.col].color != color)
			return false;
		
		return true;
	}
	
	
	//given a position and the color the player, see if this position is available
	//color in parameter is the player's color
	public boolean ifgotoPosAval(Position p, boolean color)
	{
		if(grids[p.row][p.col] == null || grids[p.row][p.col].color != color)
			return true;
		return false;
	}
	
	//given the color and position of a piece on the board, show all the positions it can move to
	public Position[] allPosAval(Position pos, boolean color)
	{
		//if the position is not a valid position on board, return null
		if(!pos.posValid())
			return null;
		//if the position is empty, return null
		if(grids[pos.row][pos.col] == null)
			return null;
		//if the position has a piece from enemy, return null
		if(grids[pos.row][pos.col].color != color)
			return null;
		//if everything goes well, then give all positions it can go +++++++++++++++
		//get the piece assigned by user
		Piece myPiece = grids[pos.row][pos.col];
		return myPiece.allmoves(this);
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	}
}
