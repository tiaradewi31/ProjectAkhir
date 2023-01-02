/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchgame;

import inputs.SoundHandler;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;
import java.util.*;
import java.util.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;

public class GamePanelMedium extends JPanel {
    Image gamebkg = new ImageIcon("images\\menuplay.png").getImage();
    Image catmedium = new ImageIcon("images\\catmedium.png").getImage();
    Image ikanputih = new ImageIcon("images\\ikanputih.png").getImage();
    Image gameOverbkg = new ImageIcon("images\\menukalah.png").getImage();
    Image tempbkg; //temporary background

    int x_catmedium, y_catmedium; //catmedium x and y  coordinates
    int x_ikanputih, y_ikanputih; // x and y coord of ikanputih
    Random rand = new Random(); // for randomizing xcoord of ikanputih

    JLabel points;
    JLabel soul;
    
    SoundHandler shButton;
    SoundHandler shKalah;

    int pointsCount = 0;
    int counter = 0;
    int soulCount = 5;
    boolean gameOver = false;

    GamePanelMedium() {
        setLayout(null);
        setFocusable(true);
        tempbkg = gamebkg;
        x_catmedium = 450;
        y_catmedium = 445;
        x_ikanputih = (int) rand.nextInt(1000);
        y_ikanputih = 0;

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

                if (ke.getKeyCode() == ke.VK_LEFT & x_catmedium > 10) {
                    x_catmedium -= 50;
                    repaint(); // redraw at new position
                }
                if (ke.getKeyCode() == ke.VK_RIGHT & x_catmedium < 1050) {
                    x_catmedium += 50; // redraw at new position
                    repaint();
                }
            }//end keypressed
        });
    }//end constructor
	
    void fallikanputih() {
        if (y_ikanputih >= 505) {
            y_ikanputih = 0;
            x_ikanputih = rand.nextInt(1000); //posisi ikanputih jatuh
            soulCount--;
        } else {
            y_ikanputih += 3; //ngatur kecepatan ikanputih
        }
        soul.setText("Soul: " + soulCount);
    }//end kecepatan ikanputih
	
    void detectCollision() {
        Rectangle catmediumRect = new Rectangle(x_catmedium, y_catmedium, 200, 65); //making a rectangle on the catmedium
        Rectangle ikanputihRect = new Rectangle(x_ikanputih, y_ikanputih, 45, 67); //making a rectangle on ikanputih

        if (ikanputihRect.intersects(catmediumRect)) {
            pointsCount += 5; // give 5 points on each catch
            points.setText("Points:" + pointsCount); // set the count
                    try {
                        shButton = new SoundHandler();
                        shButton.RunMusic("Res/meow.wav");

                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(GamePanelEasy.class.getName()).log(Level.SEVERE, null, ex);
                    }
            y_ikanputih = 0; // for next ikanputih
            x_ikanputih = rand.nextInt(1000); // again randomizing x axis of ikanputih
        }

    }//end collision detection

    void checkGameOver() {
        if (soulCount <= 0) {
            gameOver = true;
//                    try {
//                        shKalah = new SoundHandler();
//                        shKalah.RunMusic("Res/gameover.wav");
//                    } catch (LineUnavailableException ex) {
//                        Logger.getLogger(GamePanelMedium.class.getName()).log(Level.SEVERE, null, ex);
//                    }
        }
    }
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        checkGameOver();

        if (gameOver == false) {
            g2d.drawImage(tempbkg, 0, 0, null); //game background            
            setFocusable(true);
            grabFocus();

            fallikanputih();
            detectCollision();

            g2d.drawImage(ikanputih, x_ikanputih, y_ikanputih, null); //drawing ikanputih at new position
            g2d.drawImage(catmedium, x_catmedium, y_catmedium, null); //drawing catmedium
            repaint();
        } else {
            
                String name = JOptionPane.showInputDialog("Masukkan Nama Anda");
                JLabel yourName = new JLabel("Your Name :" + name);
                yourName.setFont(new Font("Serif", Font.BOLD, 20));            
                yourName.setBounds(550, 50, 200, 100);
                yourName.setForeground(Color.black);
                JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);                
                yourScore.setFont(new Font("Serif", Font.BOLD, 20));            
                yourScore.setBounds(550, 80, 200, 100);
                yourScore.setForeground(Color.black);
                g2d.drawImage(gameOverbkg, 0, 0, null);
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
        }
    }//end paintComponent
}//end class