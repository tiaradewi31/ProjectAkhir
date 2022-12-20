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
    Image gamebkg = new ImageIcon("images\\menuplay.png").getImage();
    Image cathard = new ImageIcon("images\\cathard.png").getImage();
    Image ikanputih = new ImageIcon("images\\ikanputih.png").getImage();
    Image ikanhiu = new ImageIcon("images\\ikanhiu.png").getImage();
    Image gameOverbkg = new ImageIcon("images\\menukalah.png").getImage();
    Image tempbkg; //temporary background

    int x_cathard, y_cathard; //cathard x and y  coordinates
    int x_ikanputih, y_ikanputih; // x and y coord of ikanputih
    int x_ikanhiu, y_ikanhiu; // x and y coord of ikanhiu
    Random rand = new Random(); // for randomizing xcoord of ikanputihs

    JLabel points;
    JLabel soul;

    int pointsCount = 0;
    int counter = 0;
    int soulCount = 5;

    boolean gameOver = false;

    GamePanelHard() {

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
	
	void fallikanputih() {
            if (y_ikanputih >= 505) {
                y_ikanputih = 0;
                x_ikanputih = rand.nextInt(1000); //posisi telur jatuh
                soulCount--;
            } else {
                y_ikanputih += 2; //ngatur kecepatan telur
            }
            soul.setText("Soul: " + soulCount);
        }
        void fallikanhiu() {
            if (y_ikanhiu >= 530) {
                y_ikanhiu = 0;
                x_ikanhiu = rand.nextInt(1000); //posisi telur jatuh
                soulCount--;
            } else {
                y_ikanhiu++; //ngatur kecepatan telur
            }
            soul.setText("Soul: " + soulCount);
        }
	
	void detectCollision() {
            Rectangle cathardRect = new Rectangle(x_cathard, y_cathard, 200, 65); //making a rectangle on the cathard
            Rectangle ikanputihRect = new Rectangle(x_ikanputih, y_ikanputih, 45, 67); //making a rectangle on ikanputih
            Rectangle ikanhiuRect = new Rectangle(x_ikanhiu, y_ikanhiu, 45, 67); //making a rectangle on ikanputih

            if (ikanputihRect.intersects(cathardRect)) {
                pointsCount += 5; // give 5 points on each catch
                points.setText("Points:" + pointsCount); // set the count
                y_ikanputih = 0; // for next ikanputih
                x_ikanputih = rand.nextInt(1000); // again randomizing x axis of ikanputih
            }

            if (ikanhiuRect.intersects(cathardRect)) {
                pointsCount += 10; // give 5 points on each catch
                points.setText("Points:" + pointsCount); // set the count
                y_ikanhiu = 0; // for next ikanputih
                x_ikanhiu = rand.nextInt(1000); // again randomizing x axis of ikanputih
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
                fallikanputih();
                fallikanhiu();
                detectCollision();

                g2d.drawImage(ikanputih, x_ikanputih, y_ikanputih, null); //drawing ikanputih at new position
                g2d.drawImage(ikanhiu, x_ikanhiu, y_ikanhiu, null); //drawing ikanputih at new position
                g2d.drawImage(cathard, x_cathard, y_cathard, null); //drawing cathard
                repaint();           
            } else {             
                tempbkg = gameOverbkg;
                JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);                
                yourScore.setFont(new Font("Serif", Font.BOLD, 20));            
                yourScore.setBounds(550, 50, 200, 100);
                yourScore.setForeground(Color.black);
                add(yourScore);            
                ScoreDB db = new ScoreDB();
                db.save("yok", pointsCount);
            }
	}//end paintComponent
}//end class

