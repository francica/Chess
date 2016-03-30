package Piece;

import java.util.Vector;

import object.Board;
import object.Position;

public class Bishop extends Piece{

	//constructor
	public Bishop(int r, int l, boolean c){
		color = c;
		position = new Position(r, l);
		name = "Bishop";
	}
	
	//bishop 象 斜着随便走
	@Override
	public
	Position[] allmoves(Board b) {
		
		
		int myrow = this.position.row;
		int mycol = this.position.col;
		
		Vector<Position> result = new Vector<Position>();
		//----------------------------------------------------POS 开始
		for (int i = 1; i < 8 ; i++)
			
		{
			//System.out.println(mycol +"," +myrow);
			if( myrow+i < 8 && mycol+i < 8 && b.grids[myrow+i][mycol+i] == null) 
				{//queen的右上角空的
					result.add(new Position(myrow+i, mycol+i));
				} 
			else //queen的右上角1有东西
			{
				//System.out.println(b.grids[myrow+i][mycol+i].name);
				if (myrow+i<8 && mycol+i <8  && b.grids[myrow+i][mycol+i]!= null && b.grids[myrow+i][mycol+i].color != this.color)
					{
					result.add(new Position(myrow+i, mycol+i));
					break;
					}
				else
					break;
			
			}
		}
		//------------------------------------------------------
		for (int i = 1; i < 8 ; i++)
					
				{
					if( myrow+i < 8 && mycol-i >=0 && b.grids[myrow+i][mycol-i] == null) 
						{//bishop的左上角1空的
							result.add(new Position(myrow+i, mycol - i));
						} 
					else //bishop的左上角1有东西
					{
						if (myrow+i<8 && mycol-i >=0  && b.grids[myrow+i][mycol-i]!= null && b.grids[myrow+i][mycol-i].color != this.color)
							{
							result.add(new Position(myrow+i, mycol-i));
							break;
							}
						else
							break;
						
					}
				}
		//-------------------------------------------------------
		for (int i = 1; i < 8 ; i++)
			
		{
			if( myrow - i >= 0 && mycol- i >= 0 && b.grids[myrow - i][mycol-i] == null) 
				{//bishop的左下角1空的
					result.add(new Position(myrow - i, mycol - i));
				} 
			else //bishop的左下角1有东西
			{
				if (myrow-i >= 0 && mycol-i >=0  && b.grids[myrow-i][mycol-i]!= null && b.grids[myrow-i][mycol-i].color != this.color)
					{
					result.add(new Position(myrow-i, mycol-i));
					break;
					}
				else
					break;
				
			}
		}
		//-------------------------------------------------------
		
		for (int i = 1; i < 8 ; i++)
			
		{
			if( myrow - i >= 0 && mycol + i <8 && b.grids[myrow - i][mycol + i] == null) 
				{//bishop的左上角1空的
					result.add(new Position(myrow - i, mycol + i));
				} 
			else //bishop的左上角1有东西
			{
				if (myrow-i >= 0 && mycol +i <8 && b.grids[myrow-i][mycol+i]!= null && b.grids[myrow-i][mycol+i].color != this.color)
					{
					result.add(new Position(myrow-i, mycol+i));
					break;
					}
				else
					break;
			}
		}
		
		//-------------------------------------------

		Position[] resultInArray = new Position[result.size()];
		for(int i = 0; i < result.size(); i++)
		{
			resultInArray[i] = result.elementAt(i);
		}
		return resultInArray;
		
	}
	//--------------------------------------------------------

	@Override
	public
	void move(int r, int l) {
		
		this.position.row = r;
		this.position.col = l;
		
		
		
	}

}
