package Piece;

import java.util.Vector;

import object.Board;
import object.Position;

public class customPiece extends Piece{

	public customPiece(int r, int l, boolean c){
		color = c;
		position = new Position(r, l);
		name = "Custom1";
	}
	//This Piece will move like King+Queen!
	//So it's super POWERFUL!!
	
	@Override
	public Position[] allmoves(Board b) {
		//get King's position
				int myrow = this.position.row;
				int mycol = this.position.col;
				
				Vector<Position> result = new Vector<Position>();
				//----------------------------------------------------POS 开始
				//QUEEN
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
								if (myrow+i<8 && mycol-i >=0  && b.grids[myrow+i][mycol-i]!= null && b.grids[myrow+i][mycol-i].color != this.color)
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
				
				
				//----------------------------------------------
				
				for (int i = 0; i < 8 ; i++)
					
				{
					if( myrow + i < 8 &&  b.grids[myrow + i][mycol] == null) 
						{//Queen的正上方空的
							result.add(new Position(myrow +i, mycol));
						} 
					else //queen的正上方有东西
					{
						if (myrow + i < 8 && b.grids[myrow+i][mycol] != null && b.grids[myrow+i][mycol].color != this.color)
							result.add(new Position(myrow-i, mycol));
					}
					//=====================
					if( myrow - i >= 0 &&  b.grids[myrow - i][mycol] == null) 
					{//Queen的正xia方空的
						result.add(new Position(myrow - i, mycol));
					} 
					else //queen的正xia方有东西
					{
					if (myrow - i >= 0  && b.grids[myrow-i][mycol]!= null && b.grids[myrow-i][mycol].color != this.color)
						result.add(new Position(myrow-i, mycol));
					}
					//======================
					if(mycol+i <8 && b.grids[myrow][mycol+i] == null) {//queen的右边
						result.add(new Position(myrow, mycol+i));
						} 
					else 
					{
						if ( mycol+i <8 && b.grids[myrow][mycol+i]!= null && b.grids[myrow][mycol+i].color != this.color)
							result.add(new Position(myrow, mycol+i));
					}
					//========================
					if(mycol-i >=0 && b.grids[myrow][mycol-i] == null) {//queen的左边
						result.add(new Position(myrow, mycol-1));
						} 
					else //queen的左边
					{
						if (mycol-i >=0 && b.grids[myrow][mycol-i]!= null && b.grids[myrow][mycol-i].color != this.color)
							result.add(new Position(myrow, mycol-i));
					}
				
				}
				
				//KING
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
	public void move(int r, int l) {
		
				this.position.row = r;
				this.position.col = l;
	}

}
