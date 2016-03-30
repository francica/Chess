package Piece;

import java.util.Vector;

import object.Board;
import object.Position;


public class King extends Piece{
	
	//constructor
	public King(int r, int l, boolean c){
		color = c;
		position = new Position(r, l);
		name = "King";
	}
	
	
	
	@Override
	public
	Position[] allmoves(Board b) {
		//get King's position
		int myrow = this.position.row;
		int mycol = this.position.col;
		
		Vector<Position> result = new Vector<Position>();
		//----------------------------------------------------POS 开始
		if( myrow+1<8 && mycol-1>=0 && b.grids[myrow+1][mycol-1] == null) {//king的左上角
			result.add(new Position(myrow+1, mycol-1));
			} 
		else //king的左上角有东西
		{
			if (myrow+1<8 && mycol-1>=0 && b.grids[myrow+1][mycol-1]!= null && b.grids[myrow+1][mycol-1].color != this.color)
				result.add(new Position(myrow+1, mycol-1));
		}
		//------------------------------------------------------
		if(myrow+1<8 && b.grids[myrow+1][mycol] == null) {//king的正上方
			result.add(new Position(myrow+1, mycol));
			} 
		else //king的正上角有东西
		{
			if (myrow+1<8 && b.grids[myrow+1][mycol]!= null && b.grids[myrow+1][mycol].color != this.color)
				result.add(new Position(myrow+1, mycol));
		}
		//-------------------------------------------------------
		
		if(myrow+1<8 && mycol+1<8 && b.grids[myrow+1][mycol+1] == null) {//king的右上方
			result.add(new Position(myrow+1, mycol+1));
			} 
		else //king的右上角有东西
		{
			if (myrow+1<8 && mycol+1<8 && b.grids[myrow+1][mycol+1] != null && b.grids[myrow+1][mycol+1].color != this.color)
				result.add(new Position(myrow+1, mycol+1));
		}
		//-------------------------------------------------
		
		if(mycol-1 >=0 && b.grids[myrow][mycol-1] == null) {//king的左边
			result.add(new Position(myrow, mycol-1));
			} 
		else //king的左边
		{
			if (mycol-1 >=0 && b.grids[myrow][mycol-1]!= null && b.grids[myrow][mycol-1].color != this.color)
				result.add(new Position(myrow, mycol-1));
		}
		//-------------------------------------------------
		if(mycol+1 <8 && b.grids[myrow][mycol+1] == null) {//king的右边
			result.add(new Position(myrow, mycol+1));
			} 
		else 
		{
			if (mycol+1 <8 && b.grids[myrow][mycol+1] != null && b.grids[myrow][mycol+1].color != this.color)
				result.add(new Position(myrow, mycol+1));
		}
		//-------------------------------------------------
		if(myrow-1 >=0 && mycol-1>=0 && b.grids[myrow-1][mycol-1] == null) {//king的左下边
			result.add(new Position(myrow-1, mycol-1));
			} 
		else //king的左边
		{
			if (myrow-1 >=0 && mycol-1>=0 && b.grids[myrow-1][mycol-1]!= null && b.grids[myrow-1][mycol-1].color != this.color)
				result.add(new Position(myrow-1, mycol-1));
		}
		//-------------------------------------------------------

		if(myrow-1>=0 && b.grids[myrow-1][mycol] == null) {//king的正下方
			result.add(new Position(myrow-1, mycol));
			} 
		else 		{
			if (myrow-1>=0 && b.grids[myrow-1][mycol]!= null && b.grids[myrow-1][mycol].color != this.color)
				result.add(new Position(myrow-1, mycol));
		}
		//---------------------------------------------------
		if(myrow-1>=0 && mycol+1<8 && b.grids[myrow-1][mycol+1] == null) {//king的右下边
			result.add(new Position(myrow-1, mycol+1));
			} 
		else 
		{
			if (myrow-1>=0 && mycol+1<8 && b.grids[myrow-1][mycol+1]!= null && b.grids[myrow-1][mycol+1].color != this.color)
				result.add(new Position(myrow-1, mycol+1));
		}
		//-------------------------------------------------
		
		
		//int size = result.size();
		//result.add(new Position(1, 2));
		//result.elementAt(0);		
		// if it is. a empty board


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
		
		//update old position in main????
		
		
	}

}
