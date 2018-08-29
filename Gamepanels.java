package brickfightss;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.Graphics2D;
public class Gamepanels extends JPanel implements KeyListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private boolean play = false;
	private int score=0;
	private int totalBricks =21;
	private Timer timer;
	private int delay = 8;
	private int playerX=310;
	private int  ballposX =120;
	private int  ballposY =350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	private MapGenerator map;
	
	
	public Gamepanels(){
		map = new MapGenerator(3,7);
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		timer = new Timer(delay,this);
		timer.start();
		
	}
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(1, 1,692,592);
		
		map.draw((Graphics2D)g);
		
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590, 30);
		
		g.setColor(Color.green);
		g.fillRect( playerX, 550, 100, 8);
		
		g.setColor(Color.yellow);
		g.fillOval( ballposX,ballposY, 20, 20);
		
		if(ballposY>570){
			play=false;
			ballXdir=0;
			ballYdir=0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Gameover",260, 300);
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("Press Enter To Restart",230, 350);
			
		}
		
		if(totalBricks<=0){
			g.setColor(Color.orange);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("YOU WON",260, 300);
			
			g.setColor(Color.orange);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("Press Enter To Restart",230, 350);
			
			play=false;
		}
		
		g.dispose();
		
		
	}
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play){
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
				ballYdir=-ballYdir;
				
			}
			A: for(int i=0;i<map.map.length;i++){
				  for (int j=0;j<map.map[0].length;j++){
					  if(map.map[i][j]>0){
						  int brickX=j*map.brickWidth +80;
						  int brickY=i*map.brickHeight +50;
						  int brickWidth=map.brickWidth;
						  int brickHeight=map.brickHeight;
						  
						  Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
						  Rectangle bollrect = new Rectangle(ballposX,ballposY,20,20);
						  Rectangle brickRect = rect;
						  if(bollrect.intersects(brickRect)){
							  map.setBrickValue(0, i, j);
							  totalBricks--;
							  score+=5;
							
							  if(ballposX +19 <=brickRect.x || ballposX+1 >= brickRect.x+brickRect.width){
								  ballXdir = -ballXdir;
								  if(score>=40){
									  ballXdir=-2;
									  ballYdir=-3;
								  }
							  }
							  else{
								  ballYdir=-ballYdir;
								  if(score>=40){
									  ballXdir=-2;
									  ballYdir=-3;
								  }
							  }
							  break A;
						  }
					  }
					  
				  }
				  
				  }
			
			
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0){
				ballXdir= -ballXdir;
			}
			if(ballposY<0){
				ballYdir= -ballYdir;
			}
			if(ballposX>670){
				ballXdir= -ballXdir;
			}
			
		}
		repaint();

	}
	
	 
	public void moveright(){
		play = true;
		playerX-=20;
		
	}
	public void moveleft(){
		play = true;
		playerX+=20;
		
	}

	public void keyPressed(KeyEvent e) {
        
		
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			if(playerX >= 580){
				playerX =580;
			}
			else
				moveleft();
		}
		if( e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			if(playerX<=10){
				playerX =10;
			}
			else
				moveright();
		}
		

	
		
			if( e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(!play){
					play=true;
					 playerX=310;
					 ballposX =120;
					  ballposY =350;
					ballXdir=-1;
					ballYdir=-2;
					totalBricks=21;
					score=0;
					map = new MapGenerator(3,7);
					
					repaint();
					
				}
			}
		
			
	}

	
	

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
