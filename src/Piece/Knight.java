package Piece;

import object.Board;
import object.Position;
import java.util.Vector;

public class Knight extends Piece{

	//constructor
	public Knight(int r, int l, boolean c){
		color = c;
		position = new Position(r, l);
		name = "Knight";
	}
	
	@Override
	public
	Position[] allmoves(Board b) {
		// TODO Auto-generated method stub
		int myrow = this.position.row;
		int mycol = this.position.col;
		
		Vector<Position> result = new Vector<Position>();
		//----------------------------------------------------POS 开始
		if( myrow+1<8 && mycol-2>=0 && b.grids[myrow+1][mycol-2] == null) {//knight的左上角1空的
			result.add(new Position(myrow+1, mycol-2));
			} 
		else //knight的左上角1有东西
		{
			if (myrow+1<8 && mycol-2>=0 && b.grids[myrow+1][mycol-2]!=null && b.grids[myrow+1][mycol-2].color != this.color)
				result.add(new Position(myrow+1, mycol-2));
		}
		//------------------------------------------------------
		
		if( myrow+2 < 8 && mycol-1 >= 0 && b.grids[myrow+2][mycol-1] == null) {//knight的左上角2空的
			result.add(new Position(myrow+2, mycol-1));
			} 
		else //knight的左上角2有东西
		{
			if (myrow+2 < 8 && mycol-1 >= 0 && b.grids[myrow+2][mycol-1] != null && b.grids[myrow+2][mycol-1].color != this.color)
				result.add(new Position(myrow+2, mycol-1));
		}
		//------------------------------------------------------
		
		
		if(myrow+2<8 && mycol+1<8 && b.grids[myrow+2][mycol+1] == null) {//knight的右上方
			result.add(new Position(myrow+2, mycol+1));
			} 
		else //knight的右上角有东西
		{
			if (myrow+2<8 && mycol+1<8 && b.grids[myrow+2][mycol+1]!= null && b.grids[myrow+2][mycol+1].color != this.color)
				result.add(new Position(myrow+2, mycol+1));
		}
		//-------------------------------------------------
		
		
		if(myrow+1<8 && mycol+2<8 && b.grids[myrow+1][mycol+2] == null) {//knight的右上方
			result.add(new Position(myrow+1, mycol+2));
			} 
		else //knight的右上角有东西
		{
			if (myrow+1<8 && mycol+2<8 &&  b.grids[myrow+1][mycol+2] != null&& b.grids[myrow+1][mycol+2].color != this.color)
				result.add(new Position(myrow+1, mycol+2));
		}
		
		//-------------------------------------------------
		
		if(myrow-1 >=0 && mycol-2>=0 && b.grids[myrow-1][mycol-2] == null) {//knight的左下边
			result.add(new Position(myrow-1, mycol-2));
			} 
		else //knight的左下边
		{
			if (myrow-1 >=0 && mycol-2>=0 && b.grids[myrow-1][mycol-2]!= null && b.grids[myrow-1][mycol-2].color != this.color)
				result.add(new Position(myrow-1, mycol-2));
		}
		//------------------------------------------------
		
		if(myrow-2 >=0 && mycol-1>=0 && b.grids[myrow-2][mycol-1] == null) {//knight的左下边
			result.add(new Position(myrow-2, mycol-1));
			} 
		else //knight的左下边
		{
			if (myrow-2 >=0 && mycol-1>=0 && b.grids[myrow-2][mycol-1]!= null && b.grids[myrow-2][mycol-1].color != this.color)
				result.add(new Position(myrow-2, mycol-1));
		}
		//------------------------------------------------
		
		if(myrow-1>= 0 && mycol+2 <8 && b.grids[myrow-1][mycol+2] == null) {//king的右下边
			result.add(new Position(myrow-1, mycol+2));
			} 
		else 
		{
			if (myrow-1>=0 && mycol+2<8 && b.grids[myrow-1][mycol+2]!= null && b.grids[myrow-1][mycol+2].color != this.color)
				result.add(new Position(myrow-1, mycol+2));
		}
		//-------------------------------------------------

		if(myrow-2>= 0 && mycol+1 <8 && b.grids[myrow-2][mycol+1] == null) {//king的右下边
			result.add(new Position(myrow-2, mycol+1));
			} 
		else 
		{
			if (myrow-2>=0 && mycol+1<8 && b.grids[myrow-2][mycol+1]!= null && b.grids[myrow-2][mycol+1].color != this.color)
				result.add(new Position(myrow-2, mycol+1));
		}
		//-------------------------------------------------


		//Change Vector to position array

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
		this.position.row = r;
		this.position.col = l;
		
		//update old position in main????
		
		
	}

}
