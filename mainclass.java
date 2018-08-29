package brickfightss;



import java.awt.Dimension;

import javax.swing.JFrame;

public class mainclass {
	
	
	public static void main (String[] args)
	{
		JFrame frame = new JFrame("BrickDestroyer");
		frame.setContentPane(new Gamepanels());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(10,10,700,600);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		
		
	}
		
	

}
