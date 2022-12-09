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

public class GamePanelHard extends JPanel {
    Image gamebkg = new ImageIcon("images\\gamebkg.png").getImage();
	Image basket  = new ImageIcon("images\\basket.png").getImage();
	Image egg     = new ImageIcon("images\\egg.png").getImage();
    Image ayam    = new ImageIcon("images\\ayam.png").getImage();
	Image gameOverbkg= new ImageIcon("images\\gameover.jpeg").getImage();
	Image tempbkg; //temporary background
	
	int x_basket,y_basket; //basket x and y  coordinates
	int x_egg,y_egg; // x and y coord of egg
    int x_ayam,y_ayam; // x and y coord of ayam
	Random rand = new Random(); // for randomizing xcoord of eggs
	
	JLabel time;
	JLabel points;
	JLabel soul;

	int pointsCount = 0;
	int timeleft = 100;
	int counter  = 0;
	int soulCount = 5;
	
	boolean gameOver = false;
	
	GamePanelHard(){
		
		setLayout(null);
		setFocusable(true);
		tempbkg = gamebkg;
		
		x_basket = 450; y_basket = 600;
		x_egg = (int)rand.nextInt(1000); y_egg = 0;
        x_ayam = (int)rand.nextInt(1000); y_ayam = 0;
		
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
				
				if(ke.getKeyCode() == ke.VK_LEFT & x_basket>10){
					x_basket-=50;
					repaint(); // redraw at new position
				}
				if(ke.getKeyCode() == ke.VK_RIGHT & x_basket<1000){
					x_basket+=50; // redraw at new position
					repaint();
				}
			}//end keypressed
		});	
	}//end constructor
	
	void fallEgg(){
		if(y_egg >=650){
			y_egg = 0;
			x_egg = rand.nextInt(1000); //posisi telur jatuh
		}
		else
			y_egg+=2; //ngatur kecepatan telur
	}

    void fallAyam(){
		if(y_ayam >=650){
			y_ayam = 0;
			x_ayam = rand.nextInt(1000); //posisi telur jatuh
		}
		else
			y_ayam++; //ngatur kecepatan telur
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
		Rectangle basketRect = new Rectangle(x_basket,y_basket,100,65); //making a rectangle on the basket
		Rectangle eggRect    = new Rectangle(x_egg,y_egg,45,67); //making a rectangle on egg
        Rectangle ayamRect   = new Rectangle(x_ayam,y_ayam,45,67); //making a rectangle on egg
		
		if(eggRect.intersects(basketRect)){
			pointsCount+=5; // give 5 points on each catch
			points.setText("Points:"+pointsCount); // set the count
			y_egg = 0; // for next egg
			x_egg = rand.nextInt(1000); // again randomizing x axis of egg
		}

        if(ayamRect.intersects(basketRect)){
			pointsCount+=10; // give 5 points on each catch
			points.setText("Points:"+pointsCount); // set the count
			y_ayam = 0; // for next egg
			x_ayam = rand.nextInt(1000); // again randomizing x axis of egg
		}
	}//end collision detection
	
	void opportunity(){
		if(y_egg >=650){
					y_egg = 0;
					soulCount --;
		}
		soul.setText("Soul: " +soulCount);
	}

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
			
			fallEgg();
            fallAyam();
			detectCollision();
			opportunity();
		
			g2d.drawImage(egg, x_egg, y_egg,null); //drawing egg at new position
            g2d.drawImage(ayam, x_ayam, y_ayam,null); //drawing egg at new position
			g2d.drawImage(basket, x_basket, y_basket, null); //drawing basket
		}
		
		repaint();	
	}//end paintComponent
}//end class

