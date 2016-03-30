package GUI;

import javax.swing.JButton;

public class mybutton extends JButton {
	public int c;
	public int r;
	public String a;
	
	public mybutton(int row, int col, String abc) {
		// TODO Auto-generated constructor stub
		r = row;
		c = col;
		a = abc;
		//this.setText(a);
	}
	 public void ChangeMybutton(int i, int j, String string) {
			this.r = i;
			this.c = j;
			this.a = string; 
			//this.setText(a);
		}

	
}
