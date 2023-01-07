/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;
import inputs.*;
import java.util.logging.*;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel{  
    //background
    Image gamebkg = new ImageIcon ("images\\menuplay.png").getImage();
    Image gameOverbkg = new ImageIcon("images\\gameover.png").getImage();

    //object cat
    Image cateasy = new ImageIcon ("images\\cateasy.png").getImage();
    Image catmedium = new ImageIcon ("images\\catmedium.png").getImage();
    Image cathard = new ImageIcon ("images\\cathard.png").getImage();
    
    //object ikan
    Image ikankuning = new ImageIcon ("images\\ikankuning.png").getImage();
    Image ikanputih = new ImageIcon ("images\\ikanputih.png").getImage();
    Image ikanhiu = new ImageIcon("images\\ikanhiu.png").getImage();
    
    ImageIcon exitbtn = new ImageIcon("buttons\\EXIT (2).png");
    Image tempbkg; //temporary background
    
    //koordinat x dan y cat
    int x_cateasy, y_cateasy; 
    int x_catmedium, y_catmedium;
    int x_cathard, y_cathard; 
    
    //koordinat x dan y ikan
    int x_ikankuning, y_ikankuning;
    int x_ikanputih, y_ikanputih; 
    int x_ikanhiu, y_ikanhiu; 
    Random rand = new Random();
    
    JLabel points;
    JLabel soul;
    JButton exit;

    SoundHandler shMulai;
    SoundHandler shButton;
    SoundHandler shKalah;

    static String level = "";

    int pointsCount = 0;
    int soulCount = 5;
    boolean gameOver = false;

    public GamePanel() {

        setLayout(null);
        setFocusable(true);
        tempbkg = gamebkg;

        x_cateasy = x_catmedium = x_cathard = 450;
        y_cateasy = 455;
        y_catmedium = 445;
        y_cathard = 435;
        x_ikankuning = y_ikanputih = y_ikanhiu = (int) rand.nextInt(1000);
        y_ikankuning = y_ikanputih = y_ikanhiu = 0;

        points = new JLabel("Points: 0");
        points.setFont(new Font("Serif", Font.BOLD, 20));
        points.setBounds(20, 20, 100, 20);

        soul = new JLabel("Soul : 5");
        soul.setFont(new Font("Serif", Font.BOLD, 20));
        soul.setBounds(120, 20, 150, 20);

        exit = new JButton("");
        exit.setIcon(exitbtn);
        exit.setBounds(550, 490, 180,70);

        /*adding both components in jpanel*/
        add(points);
        add(soul);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (level.equalsIgnoreCase("easy")) {
                    if (ke.getKeyCode() == ke.VK_LEFT & x_cateasy > 10) {
                        x_cateasy -= 50;
                        repaint(); // redraw at new position
                    }
                    if (ke.getKeyCode() == ke.VK_RIGHT & x_cateasy < 1050) {
                        x_cateasy += 50; // redraw at new position
                        repaint();
                    }
                } if (level.equalsIgnoreCase("medium")) {
                    if (ke.getKeyCode() == ke.VK_LEFT & x_catmedium > 10) {
                        x_catmedium -= 50;
                        repaint(); // redraw at new position
                    }
                    if (ke.getKeyCode() == ke.VK_RIGHT & x_catmedium < 1050) {
                        x_catmedium += 50; // redraw at new position
                        repaint();
                    }
                }if (level.equalsIgnoreCase("hard")) {
                    if (ke.getKeyCode() == ke.VK_LEFT & x_cathard > 10) {
                        x_cathard -= 50;
                        repaint(); // redraw at new position
                    }
                    if (ke.getKeyCode() == ke.VK_RIGHT & x_cathard < 1050) {
                        x_cathard += 50; // redraw at new position
                        repaint();
                    }
                }

                
            }//end keypressed
        });
    } // end constructor
        
    
        void fallikan() {
            if (level.equalsIgnoreCase("easy")) {
                if (y_ikankuning >= 500) {//batas jatuh ikan
                    y_ikankuning = 0;
                    x_ikankuning = rand.nextInt(1000); //posisi ikankuning jatuh
                    soulCount--;
                } else {
                    y_ikankuning += 2; //ngatur kecepatan ikankuning
                }
            } if (level.equalsIgnoreCase("medium")) {
                if (y_ikanputih >= 505) {
                    y_ikanputih = 0;
                    x_ikanputih = rand.nextInt(1000); //posisi ikanputih jatuh
                    soulCount--;
                } else {
                    y_ikanputih += 3; //ngatur kecepatan ikanputih
                }
            } if (level.equalsIgnoreCase("hard")) {
                if (y_ikanputih >= 505) {
                    y_ikanputih = 0;
                    x_ikanputih = rand.nextInt(1000); //posisi ikanputih jatuh
                    soulCount--;
                } else {
                    y_ikanputih += 3; //ngatur kecepatan ikanputih
                }
                if (y_ikanhiu >= 530) {
                    y_ikanhiu = 0;
                    x_ikanhiu = rand.nextInt(1000); //posisi telur jatuh
                    soulCount--;
                } else {
                    y_ikanhiu++; //ngatur kecepatan telur
                }                
            }
            soul.setText("Soul: " + soulCount);
        }//end kecepatan ikankuning
	
	void detectCollision() {
            Rectangle cateasyRect = new Rectangle(x_cateasy, y_cateasy, 200, 65); //making a rectangle on the cateasy
            Rectangle catmediumRect = new Rectangle(x_catmedium, y_catmedium, 200, 65); //making a rectangle on the catmedium
            Rectangle cathardRect = new Rectangle(x_cathard, y_cathard, 200, 65); //making a rectangle on the cathard
            Rectangle ikankuningRect = new Rectangle(x_ikankuning, y_ikankuning, 45, 67); //making a rectangle on ikankuning
            Rectangle ikanputihRect = new Rectangle(x_ikanputih, y_ikanputih, 45, 67); //making a rectangle on ikanputih
            Rectangle ikanhiuRect = new Rectangle(x_ikanhiu, y_ikanhiu, 45, 67); //making a rectangle on ikanhiu

            if (level.equalsIgnoreCase("easy")) {
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
            } if (level.equalsIgnoreCase("medium")) {
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
            } if (level.equalsIgnoreCase("hard")) {
                if (ikanputihRect.intersects(cathardRect)) {
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
    
                if (ikanhiuRect.intersects(cathardRect)) {
                    pointsCount += 10; // give 5 points on each catch
                    points.setText("Points:" + pointsCount); // set the count
                    try {
                        shButton = new SoundHandler();
                        shButton.RunMusic("Res/meow.wav");
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(GamePanelEasy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    y_ikanhiu = 0; // for next ikanputih
                    x_ikanhiu = rand.nextInt(1000); // again randomizing x axis of ikanputih
                }
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

                fallikan();
                detectCollision();

                if (level.equalsIgnoreCase("easy")) {
                    g2d.drawImage(ikankuning, x_ikankuning, y_ikankuning, null); //drawing ikankuning at new position
                    g2d.drawImage(cateasy, x_cateasy, y_cateasy, null); //drawing cateasy 
                } if (level.equalsIgnoreCase("medium")) {
                    g2d.drawImage(ikanputih, x_ikanputih,y_ikanputih, null); //drawing ikankuning at new position
                    g2d.drawImage(catmedium, x_catmedium,y_catmedium, null); //drawing cateasy
                } if (level.equalsIgnoreCase("hard")) {
                    g2d.drawImage(ikanputih, x_ikanputih,y_ikanputih, null); //drawing ikankuning at new position
                    g2d.drawImage(ikanhiu, x_ikanhiu, y_ikanhiu, null); //drawing ikankuning at new position
                    g2d.drawImage(cathard, x_cathard, y_cathard, null); //drawing cateasy   
                }
                repaint();
            } else {
                String name = JOptionPane.showInputDialog("Masukkan Nama Anda");
                JLabel yourName = new JLabel("Your Name :" + name);
                yourName.setFont(new Font("Poppins", Font.BOLD, 20));            
                yourName.setBounds(575, 60, 300, 100);
                yourName.setForeground(Color.black);
                JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);                
                yourScore.setFont(new Font("Poppins", Font.BOLD, 20));            
                yourScore.setBounds(575, 90, 200, 100);
                yourScore.setForeground(Color.black);
                g2d.drawImage(gameOverbkg, 0, 0, null);
                add(yourScore);
                add(yourName);      
                ScoreDB db = new ScoreDB();
                db.save(name, pointsCount);
                ArrayList<User> allUser = db.getAll();
                int a = 0;
                for(User s : allUser){
                    JLabel nama = new JLabel(" " + s.getNama());                
                    nama.setFont(new Font("Poppins", Font.BOLD, 20));
                    a += 30;
                    nama.setBounds(500, a, 300, 300);
                    JLabel score = new JLabel( " " + s.getScore());                
                    score.setFont(new Font("Poppins", Font.BOLD, 20));     
                    score.setBounds(770, a, 300, 300);
                    
                    add(nama);
                    add(score);
                } 
                    this.add(exit); //adding exit button in the panel
        
                 exit.addMouseListener(new MouseAdapter() {
                     public void mouseClicked(MouseEvent me) {
                        System.exit(0);  //exit application when exit is clicked
                         try {
                            shButton = new SoundHandler();
                            shButton.RunMusic("Res/button1.wav");
                        }catch (LineUnavailableException ex) {
                            Logger.getLogger(GamePanelMedium.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }		
	}//end paintComponent
}
