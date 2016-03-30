package Piece;

import java.util.Vector;

import object.Board;
import object.Position;

public  class customPiece2 extends Piece {
	
	public customPiece2(int r, int l, boolean c){
		color = c;
		position = new Position(r, l);
		name = "Custom2";
	}
	//This Piece will move like Bishop+Pawn's move!
	//So it's POWERFUL!!
	@Override
	public Position[] allmoves(Board b) {

		int myrow = this.position.row;
		int mycol = this.position.col;
		
		Vector<Position> result = new Vector<Position>();
		//----------------------------------------------------POS 开始
		for (int i = 0; i < 8 ; i++)
			
		{
			if( myrow+i < 8 && mycol+i < 8 && b.grids[myrow+i][mycol+i] == null) 
				{//bishop的右上角1空的
					result.add(new Position(myrow+i, mycol+i));
				} 
			else //bishop的右上角1有东西
			{
				if (myrow+i<8 && mycol+i <8  && b.grids[myrow+i][mycol+i]!= null && b.grids[myrow+i][mycol+i].color != this.color)
					result.add(new Position(myrow+i, mycol+i));
			}
		}
		//------------------------------------------------------
		for (int i = 0; i < 8 ; i++)
					
				{
					if( myrow+i < 8 && mycol-i >=0 && b.grids[myrow+i][mycol-i] == null) 
						{//bishop的左上角1空的
							result.add(new Position(myrow+i, mycol - i));
						} 
					else //bishop的左上角1有东西
					{
						if (myrow+i<8 && mycol-i >=0  && b.grids[myrow+i][mycol-i]!= null  && b.grids[myrow+i][mycol-i].color != this.color)
							result.add(new Position(myrow+i, mycol-i));
					}
				}
		//-------------------------------------------------------
		for (int i = 0; i < 8 ; i++)
			
		{
			if( myrow - i >= 0 && mycol- i >= 0 && b.grids[myrow - i][mycol-i] == null) 
				{//bishop的左下角1空的
					result.add(new Position(myrow - i, mycol - i));
				} 
			else //bishop的左下角1有东西
			{
				if (myrow-i >= 0 && mycol-i >=0  && b.grids[myrow-i][mycol-i]!= null && b.grids[myrow-i][mycol-i].color != this.color)
					result.add(new Position(myrow-i, mycol-i));
			}
		}
		//-------------------------------------------------------
		
		for (int i = 0; i < 8 ; i++)
			
		{
			if( myrow - i >= 0 && mycol + i <8 && b.grids[myrow - i][mycol + i] == null) 
				{//bishop的左上角1空的
					result.add(new Position(myrow - i, mycol + i));
				} 
			else //bishop的左上角1有东西
			{
				if (myrow-i >= 0 && mycol +i <8 && b.grids[myrow-i][mycol+i]!= null && b.grids[myrow-i][mycol+i].color != this.color)
					result.add(new Position(myrow-i, mycol+i));
			}
		}
		//-------------------------------------------PAWN MOVE
		if( myrow+1<8 && b.grids[myrow+1][mycol] == null && this.color == true) 
		{//white pawn's 上方格子是空的
		result.add(new Position(myrow+1, mycol));
		} 
		if( myrow == 1 && b.grids[myrow+2][mycol] == null && this.color == true) 
		{//white pawn在初始格子并且 上方第二个格子是空的
		result.add(new Position(myrow+2, mycol));
		} 
		if (myrow-1>=0 && b.grids[myrow-1][mycol]==null && this.color == false)
			result.add(new Position(myrow-1, mycol));
			//黑色兵的下方是空的
		
		if( myrow == 6 && b.grids[myrow-2][mycol] == null && this.color == false) 
		{//black pawn在初始格子并且 下方第二个格子是空的
		result.add(new Position(myrow-2, mycol));
		} 
		//--------------------------------

		Position[] resultInArray = new Position[result.size()];
		for(int i = 0; i < result.size(); i++)
		{
			resultInArray[i] = result.elementAt(i);
		}
		return resultInArray;
	}

	@Override
	public void move(int r, int l) {
		// TODO Auto-generated method stub
		
	}

}
