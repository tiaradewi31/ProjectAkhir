/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import inputs.SoundHandler;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;


public class GamePanelEasy extends JPanel {
    Image gamebkg = new ImageIcon("images\\menuplay.png").getImage();
    Image cateasy = new ImageIcon("images\\cateasy.png").getImage();
    Image ikankuning = new ImageIcon("images\\ikankuning.png").getImage();
    Image gameOverbkg = new ImageIcon("images\\menukalah.png").getImage();
    Image tempbkg; //temporary background

    int x_cateasy, y_cateasy; //cateasy x and y  coordinates
    int x_ikankuning, y_ikankuning; // x and y coord of ikankuning
    Random rand = new Random(); // for randomizing xcoord of ikankunings

    JLabel points;
    JLabel soul;
    
    SoundHandler shButton;
    SoundHandler shKalah;
    
    int pointsCount = 0;
    int counter = 0;
    int soulCount = 5;
    boolean gameOver = false;
	
    GamePanelEasy() {

        setLayout(null);
        setFocusable(true);
        tempbkg = gamebkg;

        x_cateasy = 450;
        y_cateasy = 455;
        x_ikankuning = (int) rand.nextInt(1000);
        y_ikankuning = 0;

        points = new JLabel("Points: 0");
        points.setFont(new Font("Serif", Font.BOLD, 20));
        points.setBounds(20, 20, 100, 20);

        soul = new JLabel("Soul : 5");
        soul.setFont(new Font("Serif", Font.BOLD, 20));
        soul.setBounds(120, 20, 150, 20);

        /*adding both components in jpanel*/
        add(points);
        add(soul);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {

                if (ke.getKeyCode() == ke.VK_LEFT & x_cateasy > 10) {
                    x_cateasy -= 50;
                    repaint(); // redraw at new position
                }
                if (ke.getKeyCode() == ke.VK_RIGHT & x_cateasy < 10500) {
                    x_cateasy += 50; // redraw at new position
                    repaint();
                }
            }//end keypressed
        });
    }//end constructor

	
	void fallikankuning() {
            if (y_ikankuning >= 500) {
                y_ikankuning = 0;
                x_ikankuning = rand.nextInt(1000); //posisi ikankuning jatuh
                soulCount--;
            } else {
                y_ikankuning += 2; //ngatur kecepatan ikankuning
            }
            soul.setText("Soul: " + soulCount);
        }//end kecepatan ikankuning
	
	void detectCollision() {
            Rectangle cateasyRect = new Rectangle(x_cateasy, y_cateasy, 200, 65); //making a rectangle on the cateasy
            Rectangle ikankuningRect = new Rectangle(x_ikankuning, y_ikankuning, 45, 67); //making a rectangle on ikankuning

            if (ikankuningRect.intersects(cateasyRect)) {
                pointsCount += 2; // give 5 points on each catch
                points.setText("Points:" + pointsCount); // set the count
                    try {
                        shButton = new SoundHandler();
                        shButton.RunMusic("Res/meow.wav");
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(GamePanelEasy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                y_ikankuning = 0; // for next ikankuning
                x_ikankuning = rand.nextInt(1000); // again randomizing x axis of ikankuning
            }
        }//end collision detection
	

	void checkGameOver() {
            if (soulCount <= 0) {
                gameOver = true;
//                    try {
//                        shKalah = new SoundHandler();
//                        shKalah.RunMusic("Res/gameover.wav");
//                    } catch (LineUnavailableException ex) {
//                        Logger.getLogger(GamePanelEasy.class.getName()).log(Level.SEVERE, null, ex);
//                    }
            }
        }
	
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;          
            checkGameOver();

            if (gameOver == false) {
                g2d.drawImage(tempbkg, 0, 0, null); //game background
                setFocusable(true);
                grabFocus();

                fallikankuning();
                detectCollision();

                g2d.drawImage(ikankuning, x_ikankuning, y_ikankuning, null); //drawing ikankuning at new position
                g2d.drawImage(cateasy, x_cateasy, y_cateasy, null); //drawing cateasy
                repaint();
            } else {
                g2d.drawImage(gameOverbkg, 0, 0, null);
                String name = JOptionPane.showInputDialog("Masukkan Nama Anda");
                JLabel yourName = new JLabel("Your Name :" + name);
                yourName.setFont(new Font("Serif", Font.BOLD, 20));            
                yourName.setBounds(550, 50, 200, 100);
                yourName.setForeground(Color.black);
                JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);                
                yourScore.setFont(new Font("Serif", Font.BOLD, 20));            
                yourScore.setBounds(550, 80, 200, 100);
                yourScore.setForeground(Color.black);
                add(yourScore);
                add(yourName);
                // JButton home = new JButton("");
                // ImageIcon homebtn = new ImageIcon("buttons\\home.png");  
                // home.setIcon(homebtn);
                // home.addMouseListener(new Click());
                // add(home);       
                ScoreDB db = new ScoreDB();
                db.save(name, pointsCount);
                ArrayList<User> allUser = db.getAll();
                for(User s : allUser){
                    JLabel nama = new JLabel(" " + s.getNama());                
                    nama.setFont(new Font("Serif", Font.BOLD, 10));     
                    nama.setBounds(550, 90, 200, 100);
                    JLabel score = new JLabel( " " + s.getScore());                
                    score.setFont(new Font("Serif", Font.BOLD, 10));     
                    score.setBounds(550, 100, 200, 100);
                }
                //repaint();
            }		
	}//end paintComponent
}//end class