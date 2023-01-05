/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel{  
    Image gamebkg = new ImageIcon ("images\\menuplay.png").getImage();
    Image cateasy = new ImageIcon ("images\\cateasy.png").getImage();
    Image ikankuning = new ImageIcon ("images\\ikankuning.png").getImage();
    Image catmedium = new ImageIcon ("images\\catmedium.png").getImage();
    Image ikanputih = new ImageIcon ("images\\ikanputih.png").getImage();
    Image cathard = new ImageIcon ("imgase\\cathard.png").getImage();
    Image ikanhiu = new ImageIcon("images\\ikanhiu.png").getImage();
    Image gameOverbkg = new ImageIcon("images\\gameover.png").getImage();
    Image tempbkg; //temporary background
    
    int x_cateasy, y_cateasy; //cateasy x and y  coordinates
    int x_catmedium, y_catmedium; //catmedium x and y  coordinates
    int x_cathard, y_cathard; //cathard x and y  coordinates
    int x_ikankuning, y_ikankuning; // x and y coord of ikankuning
    int x_ikanputih, y_ikanputih; // x and y coord of ikanputih
    int x_ikanhiu, y_ikanhiu; // x and y coord of ikanhiu
    Random rand = new Random(); // for randomizing xcoord of ikan
    
    JLabel points;
    JLabel soul;

    int pointsCount = 0;
    int counter = 0;
    int soulCount = 5;
    boolean gameOver = false;
    
    GamePanel() {
        //ini yg easy
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
        
        //ini yg medium
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
        
        //ini yg hard
        setLayout(null);
        setFocusable(true);
        tempbkg = gamebkg;

        x_cathard = 450;
        y_cathard = 435;
        x_ikanputih = (int) rand.nextInt(1000);
        y_ikanputih = 0;
        x_ikanhiu = (int) rand.nextInt(1000);
        y_ikanhiu = 0;

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

                if (ke.getKeyCode() == ke.VK_LEFT & x_cathard > 10) {
                    x_cathard -= 50;
                    repaint(); // redraw at new position
                }
                if (ke.getKeyCode() == ke.VK_RIGHT & x_cathard < 1050) {
                    x_cathard += 50; // redraw at new position
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
                y_ikankuning += 1; //ngatur kecepatan ikankuning
            }
            soul.setText("Soul: " + soulCount);
        }//end kecepatan ikankuning
	
	void detectCollision() {
            Rectangle cateasyRect = new Rectangle(x_cateasy, y_cateasy, 200, 65); //making a rectangle on the cateasy
            Rectangle ikankuningRect = new Rectangle(x_ikankuning, y_ikankuning, 45, 67); //making a rectangle on ikankuning

            if (ikankuningRect.intersects(cateasyRect)) {
                pointsCount += 2; // give 5 points on each catch
                points.setText("Points:" + pointsCount); // set the count
                y_ikankuning = 0; // for next ikankuning
                x_ikankuning = rand.nextInt(1000); // again randomizing x axis of ikankuning
            }
        }//end collision detection
        
        
    
        void checkGameOver() {
            if (soulCount <= 0) {
                gameOver = true;
            }
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(tempbkg, 0, 0, null); //game background
            checkGameOver();

            if (gameOver == false) {
                setFocusable(true);
                grabFocus();

                fallikankuning();
                detectCollision();

                g2d.drawImage(ikankuning, x_ikankuning, y_ikankuning, null); //drawing ikankuning at new position
                g2d.drawImage(cateasy, x_cateasy, y_cateasy, null); //drawing cateasy
                repaint();
            } else {
                JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);
                yourScore.setFont(new Font("Serif", Font.BOLD, 20));
                tempbkg = gameOverbkg;
                yourScore.setBounds(550, 50, 200, 100);
                yourScore.setForeground(Color.black);
                add(yourScore);
                ScoreDB db = new ScoreDB();
                db.save("hahahaaa", pointsCount);
            }		
	}//end paintComponent
}
