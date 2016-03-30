package Piece;

import object.Board;
import java.util.Vector;
import object.Position;

public abstract class Piece {
	public String name;
	public Position position;
	public boolean color;
	
	//give the board, return all positions it can go
	public abstract Position[] allmoves(Board b);
	
	//move the piece to a new postion
	//simple move action, update old position will be done in main function or board????
	
	public abstract void move(int r, int l);
	

}
