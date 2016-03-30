package Piece;

import java.util.Vector;

import object.Board;
import object.Position;

public class Pawn extends Piece {

	//constructor
	public Pawn(int r, int l, boolean c){
		color = c;
		position = new Position(r, l);
		name = "Pawn";
	}
	
	@Override
	public
	Position[] allmoves(Board b)  {

		//get pawn's position
		int myrow = this.position.row;
		int mycol = this.position.col;
		
		Vector<Position> result = new Vector<Position>();
		//----------------------------------------------------POS 开始
		if( myrow+1<8 && b.grids[myrow+1][mycol] == null && this.color == true) 
			{//white pawn's 上方格子是空的
			result.add(new Position(myrow+1, mycol));
			} 
		if( myrow == 1 && b.grids[myrow+2][mycol] == null && this.color == true) 
		{//white pawn在初始格子并且 上方第二个格子是空的
		result.add(new Position(myrow+2, mycol));
		} 
		
		if (myrow+1<8 && mycol-1 >=0 && b.grids[myrow+1][mycol-1] != null && b.grids[myrow+1][mycol-1].color == false
				&& this.color == true)
		{//白色兵的左上方旗子是黑色的
			result.add(new Position(myrow+1, mycol-1));
		}
		if (myrow+1<8 && mycol+1 <8 && b.grids[myrow+1][mycol+1] != null && b.grids[myrow+1][mycol+1].color == false
				&& this.color == true)
		{//白色兵的右上方旗子是黑色的
			result.add(new Position(myrow+1, mycol+1));
		}
		
		
		//黑色的兵-------------------------------------------
		
		if (myrow-1>=0 && b.grids[myrow-1][mycol]==null && this.color == false)
			result.add(new Position(myrow-1, mycol));
			//黑色兵的下方是空的
		
		if( myrow == 6 && b.grids[myrow-2][mycol] == null && this.color == false) 
		{//black pawn在初始格子并且 下方第二个格子是空的
		result.add(new Position(myrow-2, mycol));
		} 
	
		if (myrow-1>=0 && mycol-1 >=0 && b.grids[myrow-1][mycol-1]  != null && b.grids[myrow-1][mycol-1].color == true
				&& this.color == false)
		{//黑色兵的左下方旗子是白色的
			result.add(new Position(myrow-1, mycol-1));
		}
		if (myrow-1>=0 && mycol+1 <8 && b.grids[myrow-1][mycol+1]  != null && b.grids[myrow-1][mycol+1].color == true
				&& this.color == false)
		{//黑色兵的右下方旗子是白色的
			result.add(new Position(myrow-1, mycol+1));
		}
		
		Position[] resultInArray = new Position[result.size()];
		for(int i = 0; i < result.size(); i++)
		{
			resultInArray[i] = result.elementAt(i);
		}
		return resultInArray;
	}

	@Override
	public
	void move(int r, int l) {
		// TODO Auto-generated method stub
		this.position.row = r;
		this.position.col = l;
	}

}
