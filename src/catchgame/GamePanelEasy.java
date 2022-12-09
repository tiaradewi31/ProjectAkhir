/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GamePanelEasy extends JPanel {
    Image gamebkg = new ImageIcon("images\\menuplay.png").getImage();
	Image cateasy  = new ImageIcon("images\\cateasy.png").getImage();
	Image ikankuning     = new ImageIcon("images\\ikankuning.png").getImage();
	Image gameOverbkg= new ImageIcon("images\\menukalah.png").getImage();
	Image tempbkg; //temporary background
	
	int x_cateasy,y_cateasy; //cateasy x and y  coordinates
	int x_ikankuning,y_ikankuning; // x and y coord of ikankuning
	Random rand = new Random(); // for randomizing xcoord of ikankunings
	
	JLabel time;
	JLabel points;
	JLabel soul;
	
	int pointsCount = 0;
	int timeleft = 100;
	int counter  = 0;
	int soulCount = 5;
	
	boolean gameOver = false;
	
	GamePanelEasy(){
		
		setLayout(null);
		setFocusable(true);
		tempbkg = gamebkg;
		
		x_cateasy = 450; y_cateasy = 455;
		x_ikankuning = (int)rand.nextInt(1000); y_ikankuning = 0;
		
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
				
				if(ke.getKeyCode() == ke.VK_LEFT & x_cateasy>10){
					x_cateasy-=50;
					repaint(); // redraw at new position
				}
				if(ke.getKeyCode() == ke.VK_RIGHT & x_cateasy<10500){
					x_cateasy+=50; // redraw at new position
					repaint();
				}
			}//end keypressed
		});	
	}//end constructor
	
	void fallikankuning(){
		if(y_ikankuning >=500){
			y_ikankuning = 0;
			x_ikankuning = rand.nextInt(1000); //posisi telur jatuh
			soulCount --;
		}
		else{
			y_ikankuning+=1; //ngatur kecepatan telur
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
		Rectangle cateasyRect = new Rectangle(x_cateasy,y_cateasy,200,65); //making a rectangle on the cateasy
		Rectangle ikankuningRect    = new Rectangle(x_ikankuning,y_ikankuning,45,67); //making a rectangle on ikankuning
		
		if(ikankuningRect.intersects(cateasyRect)){
			pointsCount+=2; // give 5 points on each catch
			points.setText("Points:"+pointsCount); // set the count
			y_ikankuning = 0; // for next ikankuning
			x_ikankuning = rand.nextInt(1000); // again randomizing x axis of ikankuning
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
			
			fallikankuning();
			detectCollision();
		
			g2d.drawImage(ikankuning, x_ikankuning, y_ikankuning,null); //drawing ikankuning at new position
			g2d.drawImage(cateasy, x_cateasy, y_cateasy, null); //drawing cateasy
		}
		
		repaint();	
	}//end paintComponent
}//end class

