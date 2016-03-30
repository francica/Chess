package object;

/*
 * this class store two integers that give the a specific postion
 */
public class Position {
	public int row;
	public int col;
	
	public Position(int r, int c)
	{
		row = r;
		col = c;
	}
	
	public boolean posValid()
	{
		if(row > 7 || row < 0 || col > 7 || col < 0 )
			return false;
		
		return true;
	}
}
