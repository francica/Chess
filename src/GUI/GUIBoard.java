package GUI;

import GUI.mybutton;
import Piece.Bishop;
import Piece.King;
import Piece.Knight;

import Piece.Queen;
import Piece.Rook;
import main.MainGame;
import object.Board;
import object.Position;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUIBoard{
	
	//used to put all buttons
	private static mybutton [][] buttonBoard = null;
	//panel
	private static JPanel guiBoard = null;
	private static JPanel textBoard = null;
	private static JPanel forfeitBoard = null;
	private static JLabel textLabel = null;

	//the main game logic
	private static MainGame game = new MainGame();
	private static Stack<MainGame> prevGames = new Stack<MainGame>();
	private static mybutton chosenButton = null;
	static JFrame mainframe = null;
	static String userA = null, userB = null;
	static int UserAScore = 0, UserBScore= 0;
	
	static int conditionNum = 1;
	/*
	 *  conditionNum 0: the thread is busy, you need to wait to give another click
	 * 				1: waiting for white user to choose the piece he want to move
	 * 				2: waiting for black user to choose the piece he want to move
	 * 				3: waiting for white user to choose the destination of the moving piece
	 * 				4: waiting for black user to choose the destination of the moving piece
	*/				
	public static void main(String[] args) {
	      
        mainframe = new JFrame("Chess Game");
        guiBoard = new JPanel();
        textBoard = new JPanel();//display information
        		
        guiBoard.setBounds(130, 60, 700, 700);
        textBoard.setBounds(0, 0, 540, 50);
        textLabel = new JLabel("New Game! White Player Please Choose Piece!");
        textLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        textBoard.add(textLabel);

        guiBoard.setLayout(null);
        //textBoard.setLayout(null);
        buttonArrayMaker();

        mainframe.setSize(1000,900);
        mainframe.add(guiBoard);
        mainframe.add(textBoard);
        setButtonOnclickFunc();
        
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpMenu(mainframe);
        //FORFEIT FUNCTION
        buildNewFuncBoard();
        
        saveBoard();
        mainframe.setVisible(true);
      
}
	
	public static void buttonArrayMaker(){
		chosenButton = null;
		
		buttonBoard = new mybutton[8][8];
		
		for (int i = 0; i<8; i++)
			for(int j = 0; j<8; j++)
			{
				buttonBoard[i][j] = new mybutton(i, j, "_");;
				buttonBoard[i][j].setBounds(j * 80, (7 - i) * 80, 80, 80);
				if((i%2 == 0 && j%2 == 0)|(i%2 ==1 && j%2 ==1))
				{
					buttonBoard[i][j].setBackground(Color.GRAY);
					buttonBoard[i][j].setOpaque(true);
					buttonBoard[i][j].setBorderPainted(false);
				}
				else
				{
					buttonBoard[i][j].setBackground(Color.WHITE);
					buttonBoard[i][j].setOpaque(true);
					buttonBoard[i][j].setBorderPainted(false);
				}
				
				buttonBoard[i][j].addActionListener( new ActionListener(){
					
					public void actionPerformed(ActionEvent event) {
		            	Object source = event.getSource();
		                if (source instanceof mybutton) {
		                	mybutton btn = (mybutton)source;
		                    // Go ahead and do what you like
		                }
		           }
				}
				
				);
				guiBoard.add(buttonBoard[i][j]);
			}
		
		//put pieces on the board ++++++++++++++++++++++++++++++
				//white pieces (as white)
				SetImage();

	}

	
	public static void SetImage(){
		
		buttonBoard[0][0] .setIcon(new ImageIcon("resources/Rook-64.png"));//车 true is white;
		buttonBoard[0][1] .setIcon(new ImageIcon("resources/Knight-64.png"));//马
		buttonBoard[0][2] .setIcon(new ImageIcon("resources/Bishop-64.png"));//象斜着走
		buttonBoard[0][3] .setIcon(new ImageIcon("resources/King-64.png"));//车+象
		buttonBoard[0][4] .setIcon(new ImageIcon("resources/Queen-64.png"));//四周一格
		buttonBoard[0][5] .setIcon(new ImageIcon("resources/Bishop-64.png"));//象斜着走
		buttonBoard[0][6] .setIcon(new ImageIcon("resources/Knight-64.png"));//马
		buttonBoard[0][7] .setIcon(new ImageIcon("resources/Rook-64.png"));//车 true is white;
		
		for (int i = 0; i < 8; i++)
		{
			buttonBoard[1][i] .setIcon(new ImageIcon("resources/Pawn-64.png"));//兵 white
		}
		

		//black pieces (as black)
		buttonBoard[7][0].setIcon(new ImageIcon("resources/Rook-64-b.png"));
		buttonBoard[7][1].setIcon(new ImageIcon("resources/Knight-64-b.png"));//马
		buttonBoard[7][2].setIcon(new ImageIcon("resources/Bishop-64-b.png"));//象斜着走
		buttonBoard[7][3].setIcon(new ImageIcon("resources/King-64-b.png"));//车+象
		buttonBoard[7][4].setIcon(new ImageIcon("resources/Queen-64-b.png"));//四周一格
		buttonBoard[7][5].setIcon(new ImageIcon("resources/Bishop-64-b.png"));;//象斜着走
		buttonBoard[7][6].setIcon(new ImageIcon("resources/Knight-64-b.png"));;//马
		buttonBoard[7][7].setIcon(new ImageIcon("resources/Rook-64-b.png"));;//车 true is white;
		
		for (int i = 0; i < 8; i++)
		{
			buttonBoard[6][i] .setIcon(new ImageIcon("resources/Pawn-64-b.png"));//兵 black
		}
		
		for(int i = 2; i < 6; i++)
			{
				for(int j = 0; j <8; j++)	
				{
					buttonBoard[i][j].setIcon(null);
				}
			}
		
	}
	
   private static void setUpMenu(JFrame window) {
//System.out.println("Menubar???????????");
        JMenuBar menubar = new JMenuBar();
        JMenu score = new JMenu("Score Records");
        JMenuItem addname = new JMenuItem("Add User Name");
        JMenuItem scoreRecord = new JMenuItem("Current Score Record");
        
        addname.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userA = JOptionPane.showInputDialog(mainframe,
                        "What is UserA's name(White)?", null);
                userB = JOptionPane.showInputDialog(mainframe,
                        "What is UserB's name(Black)?", null);
            }
        });
        
        scoreRecord.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JOptionPane.showMessageDialog(null, userA + "'s score:" + UserAScore + "\n"
            								+ userB + "'s score:" + UserBScore + "\n");
            }
        });
        
        
        
        score.add(addname);
        score.add(scoreRecord);
        menubar.add(score);
        
        window.setJMenuBar(menubar);
    }

	
	private static void buildNewFuncBoard() {
		  forfeitBoard = new JPanel();
          
          JButton forfeit = new JButton("I forfeit.");
          JButton Undo = new JButton("Undo");
          JButton nolose = new JButton("No one Lose!");
          
         	
          forfeitBoard.add(Undo);
          forfeitBoard.add(forfeit);
          forfeitBoard.add(nolose);
          mainframe.add(forfeitBoard, BorderLayout.EAST);
          //mainframe.pack();
          

          forfeit.addActionListener(new java.awt.event.ActionListener() {
              @Override
              public void actionPerformed(java.awt.event.ActionEvent evt) {
              	if(conditionNum == 0)
              		 {
              			JOptionPane.showMessageDialog(null, "Busy, try again!");
              			return;
              		 }
              	else if(conditionNum ==1 || conditionNum==3)
              		{
              			UserBScore++;
              			JOptionPane.showMessageDialog(null, "Black User Win!");
              		}
              	else if(conditionNum ==2 || conditionNum ==4)
              		{
              			UserAScore++;
              			JOptionPane.showMessageDialog(null, "White User Win!");
              		}
              	
              	//Start to get a new Chess board
              	game = new MainGame();
              	chosenButton = null;
          		
                  SetImage();	
              	conditionNum = 1;
              	
              }
          });
          
          
          
          Undo.addActionListener(new java.awt.event.ActionListener() {
              @Override
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  //////////////////////////////////////////////////////////////////////////////////
            	  if(conditionNum != 0  && goBack())
            	  {
            		  //set UI already
            	  }
            	  else{
            		  JOptionPane.showMessageDialog(null, "You are already at original state or system is busy");
            	  }
              }
          });
          
          nolose.addActionListener(new java.awt.event.ActionListener() {
              @Override
              public void actionPerformed(java.awt.event.ActionEvent evt) {
              	
              	JOptionPane.showMessageDialog(null, "No one lose! Let's start a new game!");
              		
              	//Start to get a new Chess board
              	game = new MainGame();
              	chosenButton = null;
                  SetImage();	
              	conditionNum = 1;
              	
              	
              }
          });
		
	}
	
	public static boolean goBack()
	{
		conditionNum = 0;
		if(prevGames.isEmpty())
		{
			saveBoard();
			if(game.color)//if white is moving
			{
				conditionNum = 1;
			}
			else//if black is moving
			{
				conditionNum = 2;
			}
			game.printBoard();
			return false;
		}
		else{
			game = prevGames.pop();
			//set UI
			for(int i = 0; i < 8; i ++)
			{
				for(int j = 0; j < 8; j++)
				{
					if(game.gameBoard.grids[i][j] == null)
					{
						buttonBoard[i][j] .setIcon(null);
					}
					else if(game.gameBoard.grids[i][j].name.equals("Pawn"))
					{
						if(game.gameBoard.grids[i][j].color)
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Pawn-64.png"));
						}
						else
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Pawn-64-b.png"));
						}
					}
					else if(game.gameBoard.grids[i][j].name.equals("Rook"))
					{
						if(game.gameBoard.grids[i][j].color)
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Rook-64.png"));
						}
						else
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Rook-64-b.png"));
						}
					}
					else if(game.gameBoard.grids[i][j].name.equals("Bishop"))
					{
						if(game.gameBoard.grids[i][j].color)
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Bishop-64.png"));
						}
						else
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Bishop-64-b.png"));
						}
					}
					else if(game.gameBoard.grids[i][j].name.equals("Knight"))
					{
						if(game.gameBoard.grids[i][j].color)
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Knight-64.png"));
						}
						else
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Knight-64-b.png"));
						}
					}
					else if(game.gameBoard.grids[i][j].name.equals("Queen"))
					{
						if(game.gameBoard.grids[i][j].color)
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/King-64.png"));
						}
						else
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/King-64-b.png"));
						}
					}
					else if(game.gameBoard.grids[i][j].name.equals("King"))
					{
						if(game.gameBoard.grids[i][j].color)
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Queen-64.png"));
						}
						else
						{
							buttonBoard[i][j] .setIcon(new ImageIcon("resources/Queen-64-b.png"));
						}
					}
					
				}
			}
			if(game.color)//if white is moving
			{
				conditionNum = 1;
			}
			else//if black is moving
			{
				conditionNum = 2;
			}
			game.printBoard();
			
			return true;
		}
	}

	public static void saveBoard()
	{
		MainGame prevGame = game.deepCopy();
		prevGames.push(prevGame);
	}

	//set the onclick function of button
	static void  setButtonOnclickFunc()
	{
		for(int i = 0; i < 8 ; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				buttonBoard[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(conditionNum == 0)//thread busy
							//TODO Print that I am busy!!!
							return;
						if(conditionNum == 1)//white, choose piece
						{
							conditionNum = 0;
							
							if(chosenButton != null)
							{
								if((chosenButton.r%2 == 0 && chosenButton.c%2 == 0)|
										(chosenButton.r%2 ==1 && chosenButton.c%2 ==1))
								{
									chosenButton.setBackground(Color.GRAY);
								}
								else
								{
									chosenButton.setBackground(Color.WHITE);
								}
							}
							
							chosenButton = null;
							//get the button we clicked
							Object source = e.getSource();
			                if (source instanceof mybutton) {
			                	chosenButton = (mybutton)source;
			                	
			                	}
			                else
			                {
			                	textLabel.setText("Did not get right piece, White Player try again!");
			                	conditionNum = 1;
			                	return;
			                }
			                
			                int row = chosenButton.r;
			                int column = chosenButton.c;
			                
							new Position(row, column);
			                
			                Position[] allpos = game.whiteUserChoosePiece(row, column);
			                if (allpos == null)
			                {
			                	//print something to tell user 
			                	textLabel.setText("This piece does not have valid move, White Player please try again!");

			                	conditionNum = 1;
			                	return;
			                }
			                //highlight all available positions
			                chosenButton.setBackground(Color.pink);
			                conditionNum = 3;
			                return;
						}
						if(conditionNum == 2)//black, choose piece
						{
							conditionNum = 0;
							if(chosenButton != null)
							{
								if((chosenButton.r%2 == 0 && chosenButton.c%2 == 0)|
										(chosenButton.r%2 ==1 && chosenButton.c%2 ==1))
								{
									chosenButton.setBackground(Color.GRAY);
								}
								else
								{
									chosenButton.setBackground(Color.WHITE);
								}
							}
							chosenButton = null;
							//get the button we clicked
							Object source = e.getSource();
			                if (source instanceof mybutton) {
			                	chosenButton = (mybutton)source;
			                	}
			                else
			                {
			                	textLabel.setText("Did not get right piece, Black Player try again!");

			                	conditionNum = 2;
			                	return;
			                }
			                
			                int row = chosenButton.r;
			                int column = chosenButton.c;
			                
			                new Position(row, column);
			                
			                Position[] allpos = game.blackUserChoosePiece(row, column);
			                if (allpos == null)
			                {
			                	//print something to tell user 
			                	textLabel.setText("This piece does not have valid move, Black Player please try again!");

			                	conditionNum = 2;
			                	return;
			                }
			                //highlight all available positions
			                chosenButton.setBackground(Color.pink);
			                conditionNum = 4;
			                return;
						}
						if(conditionNum == 3)//white, choose target
						{
							conditionNum = 0;
							if(chosenButton != null)
							{
								if((chosenButton.r%2 == 0 && chosenButton.c%2 == 0)|
										(chosenButton.r%2 ==1 && chosenButton.c%2 ==1))
								{
									chosenButton.setBackground(Color.GRAY);
								}
								else
								{
									chosenButton.setBackground(Color.WHITE);
								}
							}
							mybutton btn = null;
							//get the button we clicked
							Object source = e.getSource();
			                if (source instanceof mybutton) {
			                	btn = (mybutton)source;
			                	}
			                else
			                {//we did not click on the right place
			                	textLabel.setText("Did not get right piece, White Player try again!");
			                	conditionNum = 3;
			                	return;
			                }
			                
							int row = btn.r;
				            int column = btn.c;
							int res= game.whiteUserTarget(row, column);
							//show user move successful
							switch (res) {
							case 1://succeesfully moved piece
			                	textLabel.setText("Move successful!");

								//do modification to panel according to chosenPiecePos
								btn.setIcon(chosenButton.getIcon());
								chosenButton.setIcon(null);
								saveBoard();
								conditionNum = 2;
								return;
							
							case 2://successfully moved and check
								textLabel.setText("Moved and checked!!!!!");
								btn.setIcon(chosenButton.getIcon());
								chosenButton.setIcon(null);
								saveBoard();
								//show the black player that he is checked
								conditionNum = 2;
								return;
								
							case 3:
								textLabel.setText("Checkmate!!!!!");
								btn.setIcon(chosenButton.getIcon());
								chosenButton.setIcon(null);
								
								JOptionPane.showMessageDialog(null,"End of Game!","end",JOptionPane.WARNING_MESSAGE);
								game = new MainGame();
				              	chosenButton = null;
				                
				              	conditionNum = 1;
								prevGames = new Stack<MainGame>();
								UserAScore++;
								SetImage();	
								//end the game
								//restart the game
								return;
								
							case 0:
								//navigate back to the state this user can choose piece again
								textLabel.setText("Not valid destination, please choose again!!!!!");
								conditionNum = 1;
								return;

							default:
								System.out.println("Should not be here!");
								return;
								
							}
							
						}
						if(conditionNum == 4)//black, choose target
						{
							conditionNum = 0;
							if(chosenButton != null)
							{
								if((chosenButton.r%2 == 0 && chosenButton.c%2 == 0)|
										(chosenButton.r%2 ==1 && chosenButton.c%2 ==1))
								{
									chosenButton.setBackground(Color.GRAY);
								}
								else
								{
									chosenButton.setBackground(Color.WHITE);
								}
							}
							mybutton btn = null;
							//get the button we clicked
							Object source = e.getSource();
			                if (source instanceof mybutton) {
			                	btn = (mybutton)source;
			                	}
			                else
			                {
			                	textLabel.setText("Did not get right piece, Black Player try again!");
			                	conditionNum = 4;
			                	return;
			                }
			                
							int row = btn.r;
				            int column = btn.c;
							int res= game.blackUserTarget(row, column);
							//show user move successful
							switch (res) {
							case 1:
								//do modification to panel according to chosenPiecePos
								textLabel.setText("Moved!");
								btn.setIcon(chosenButton.getIcon());
								chosenButton.setIcon(null);
								saveBoard();
								conditionNum = 1;
								return;
							
							case 2:
								textLabel.setText("Moved and checked!!!!");
								btn.setIcon(chosenButton.getIcon());
								chosenButton.setIcon(null);
								saveBoard();
								//show the black player that he is checked
								conditionNum = 1;
								return;
								
							case 3:
								textLabel.setText("Checkmate!!");
								btn.setIcon(chosenButton.getIcon());
								chosenButton.setIcon(null);
								saveBoard();
								
								JOptionPane.showMessageDialog(null,"End of Game!","end",JOptionPane.WARNING_MESSAGE);
								game = new MainGame();
				              	chosenButton = null;
				                
				              	conditionNum = 1;
								prevGames = new Stack<MainGame>();
								UserBScore++;
								SetImage();	
								
								//end the game
								return;
								
							case 0:
								textLabel.setText("Not valid destination, please choose again!!!!!");
								conditionNum = 2;
								return;

							default:
								System.out.println("Should not be here!");
								return;
								
							}
						}
						
					}
				});
			}
		}
	}


}


/*
buttonBoard[0][0] . ChangeMybutton(0, 0, "W_rook");//车 true is white;
buttonBoard[0][1] . ChangeMybutton(0, 1, "W_knight");//马
buttonBoard[0][2] . ChangeMybutton(0, 2, "W_bishop");//象斜着走
buttonBoard[0][3] . ChangeMybutton(0, 3, "W_queen");//车+象
buttonBoard[0][4] . ChangeMybutton(0, 4, "W_king");//四周一格
buttonBoard[0][5] . ChangeMybutton(0, 5, "W_bishop");//象斜着走
buttonBoard[0][6] . ChangeMybutton(0, 6, "W_knight");//马
buttonBoard[0][7] . ChangeMybutton(0, 7, "W_rook");//车 true is white;

buttonBoard[1][0] . ChangeMybutton(1, 0, "W_pawn");//兵 true is white;
buttonBoard[1][1] . ChangeMybutton(1, 1, "W_pawn");//兵 true is white;
buttonBoard[1][2] . ChangeMybutton(1, 2, "W_pawn");//兵 true is white;
buttonBoard[1][3] . ChangeMybutton(1, 3, "W_pawn");//兵 true is white;
buttonBoard[1][4] . ChangeMybutton(1, 4, "W_pawn");//兵 true is white;
buttonBoard[1][5] . ChangeMybutton(1, 5, "W_pawn");//兵 true is white;
buttonBoard[1][6] . ChangeMybutton(1, 6, "W_pawn");//兵 true is white;
buttonBoard[1][7] . ChangeMybutton(1, 7, "W_pawn");//兵 true is white;

//black pieces (as black)
buttonBoard[7][0] . ChangeMybutton(7, 0, "rook");//车 false is black
buttonBoard[7][1] . ChangeMybutton(7, 1, "knight");//马
buttonBoard[7][2] . ChangeMybutton(7, 2, "bishop");//象斜着走
buttonBoard[7][3] . ChangeMybutton(7, 3, "queen");//车+象
buttonBoard[7][4] . ChangeMybutton(7, 4, "king");//四周一格
buttonBoard[7][5] . ChangeMybutton(7, 5, "bishop");//象斜着走
buttonBoard[7][6] . ChangeMybutton(7, 6, "knight");//马
buttonBoard[7][7] . ChangeMybutton(7, 7, "rook");//车 true is white;

buttonBoard[6][0] . ChangeMybutton(6, 0, "pawn");//兵 black
buttonBoard[6][1] . ChangeMybutton(6, 1, "pawn");//兵 black
buttonBoard[6][2] . ChangeMybutton(6, 2, "pawn");//兵 black
buttonBoard[6][3] . ChangeMybutton(6, 3, "pawn");//兵 black
buttonBoard[6][4] . ChangeMybutton(6, 4, "pawn");//兵 black
buttonBoard[6][5] . ChangeMybutton(6, 5, "pawn");//兵 black
buttonBoard[6][6] . ChangeMybutton(6, 6, "pawn");//兵 black
buttonBoard[6][7] . ChangeMybutton(6, 7, "pawn");//兵 black
*/
//++++++++++++++++++++++++++++++++++++++++++++++++++++