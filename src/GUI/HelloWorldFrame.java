package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import statements
//Check if window closes automatically. Otherwise add suitable code
public class HelloWorldFrame extends JFrame {
	public static void main(String args[]) {
		//frame
		HelloWorldFrame helloWorldFrame = new HelloWorldFrame();
		
		//panel
		JPanel pane = (JPanel)helloWorldFrame.getContentPane();
		pane.setBounds(0, 0, 800, 800);
		pane.setLayout(null);
        //buttons
		mybutton[][] buttons = new mybutton[8][8];
		//put buttons in the right pos
		for(int i = 0; i < 8 ; i++)
		{
			for(int j = 0; j < 8 ;j ++)
			{
				
				buttons[i][j].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent event) {
		            	Object source = event.getSource();
		                if (source instanceof mybutton) {
		                	mybutton btn = (mybutton)source;
		                
		                    // Go ahead and do what you like
		                }
		            }
		        });
				buttons[i][j].setBounds(j * 100, (7 - i) * 100, 100, 100);
				pane.add(buttons[i][j]);
			}
		}
		
		
		helloWorldFrame.setVisible(true);
	}
    
    
	HelloWorldFrame() {		
		this.setSize(1000, 1000);
	}
	
	
}