/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchgame;

/**
 *
 * @author LENOVO
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GamePanelMedium extends JPanel {
    Image gamebkg = new ImageIcon("images\\menuplay.png").getImage();
	Image catmedium  = new ImageIcon("images\\catmedium.png").getImage();
	Image ikanputih     = new ImageIcon("images\\ikanputih.png").getImage();
	Image gameOverbkg= new ImageIcon("images\\menukalah.png").getImage();
	Image tempbkg; //temporary background
	
	int x_catmedium,y_catmedium; //catmedium x and y  coordinates
	int x_ikanputih,y_ikanputih; // x and y coord of ikanputih
	Random rand = new Random(); // for randomizing xcoord of ikanputihs
	
	JLabel time;
	JLabel points;
	JLabel soul;
	
	int pointsCount = 0;
	int timeleft = 100;
	int counter  = 0;
	int soulCount = 5;
	
	boolean gameOver = false;
	
	GamePanelMedium(){
		
		setLayout(null);
		setFocusable(true);
		tempbkg = gamebkg;
		
		x_catmedium = 450; y_catmedium = 445;
		x_ikanputih = (int)rand.nextInt(1000); y_ikanputih = 0;
		
	    time = new JLabel("Time: 100");
		time.setBounds(20, 10, 50, 20); //setting the time label on screen
	    
	    
	    points = new JLabel("Points: 0");
		points.setBounds(100,10,100,20);
		
		soul = new JLabel ("Soul : 5");
        soul.setBounds(200, 10, 150, 20);
		
		/*adding both components in jpanel*/
		add(time);
		add(points);
		add(soul);
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				
				if(ke.getKeyCode() == ke.VK_LEFT & x_catmedium>10){
					x_catmedium-=50;
					repaint(); // redraw at new position
				}
				if(ke.getKeyCode() == ke.VK_RIGHT & x_catmedium<1050){
					x_catmedium+=50; // redraw at new position
					repaint();
				}
			}//end keypressed
		});	
	}//end constructor
	
	void fallikanputih(){
		if(y_ikanputih >=505){
			y_ikanputih = 0;
			x_ikanputih = rand.nextInt(1000); //posisi telur jatuh
			soulCount --;
		}
		else{
			y_ikanputih+=2; //ngatur kecepatan telur
		}
		soul.setText("Soul: " +soulCount);
	}
	
	void updateTime(){
		counter++;
		if(counter == 100) //we count to 60 and then dec timeleft by 1 for slowing speed
		{
		   timeleft--;  //dec time left after 60 counts
		   counter = 0; //reset counter
		}
		time.setText("Time:"+timeleft);
	}
	
	void detectCollision(){
		Rectangle catmediumRect = new Rectangle(x_catmedium,y_catmedium,200,65); //making a rectangle on the catmedium
		Rectangle ikanputihRect    = new Rectangle(x_ikanputih,y_ikanputih,45,67); //making a rectangle on ikanputih
		
		if(ikanputihRect.intersects(catmediumRect)){
			pointsCount+=5; // give 5 points on each catch
			points.setText("Points:"+pointsCount); // set the count
			y_ikanputih = 0; // for next ikanputih
			x_ikanputih = rand.nextInt(1000); // again randomizing x axis of ikanputih
		}

	}//end collision detection

	void checkGameOver(){
		if(timeleft <= 0 || soulCount <= 0)
		{
			JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);
			tempbkg = gameOverbkg;
			yourScore.setBounds(400, 400, 200, 100);
			gameOver = true;
			yourScore.setForeground(Color.RED);
			add(yourScore);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(tempbkg,0,0,null); //game background
		checkGameOver();
		
		if(gameOver == false){
			setFocusable(true);
			grabFocus();
			updateTime();
			
			fallikanputih();
			detectCollision();

			g2d.drawImage(ikanputih, x_ikanputih, y_ikanputih,null); //drawing ikanputih at new position
            //g2d.drawImage(ayam, x_ayam, y_ayam,null); //drawing ikanputih at new position
			g2d.drawImage(catmedium, x_catmedium, y_catmedium, null); //drawing catmedium
		}
		
		repaint();	
	}//end paintComponent
}//end class

