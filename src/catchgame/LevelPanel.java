/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchgame;

import inputs.SoundHandler;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

public class LevelPanel extends JPanel {

    JButton easy = new JButton("");
    JButton medium = new JButton("");
    JButton hard = new JButton("");
    JButton back = new JButton("");



    Image levelbkg = new ImageIcon("images\\menulevel (2).png").getImage();  //level background

    /* Setting icons on buttons */
    ImageIcon easybtn = new ImageIcon("buttons\\easy.png");
    ImageIcon mediumbtn = new ImageIcon("buttons\\medium.png");
    ImageIcon hardbtn = new ImageIcon("buttons\\hard.png");
    ImageIcon backbtn = new ImageIcon("buttons\\back.png");


    JPanel center = new JPanel(); //adding another jpanel in a panel for boxLayout
    SoundHandler shButton;
    SoundHandler shMulai;
    MenuPanel mp;

    LevelPanel(MenuPanel mp){
    //LevelPanel() {
        this.mp = mp;
//        mp.shMenu.StopMusic();

        /*center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));//setting box layout
        add(center); //adding the panel to another JPanel

        /* setting icons on buttons */
        easy.setIcon(easybtn);
        medium.setIcon(mediumbtn);
        hard.setIcon(hardbtn);
        back.setIcon(backbtn);

        /* adding the buttons in the panel */
        setLayout(null);
        
        this.add(easy);
        this.add(medium);
        this.add(hard);
        this.add(back);

        
        easy.setBounds(550, 150, 180,70);
        medium.setBounds(550,250, 180,70);
        hard.setBounds(550, 350, 180,70);
        back.setBounds(550, 450, 180,70);


        /* adding mouseListeners on buttons */
        easy.addMouseListener(new Click());
        medium.addMouseListener(new Click());
        hard.addMouseListener(new Click());
        back.addMouseListener(new Click());
    }//end constructor

    class Click extends MouseAdapter { //internal friendly class

        public void mouseClicked(MouseEvent me) {
            if (me.getSource() == easy) {
                try {    
                    shButton = new SoundHandler();
                    shButton.RunMusic("Res/button2.wav");
                    mp.shMenu.StopMusic();
                    shMulai = new SoundHandler();
                    shMulai.RunMusic("Res/play.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(LevelPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                GamePanel gp = new GamePanel();
                Game.cards.add(gp, "GamePanel");
                GamePanel.level = "easy";
                Game.cl.show(Game.cards, "GamePanel"); //show gamePanel when play is clicked
            }
            else if (me.getSource() == medium) {
                try {    
                    shButton = new SoundHandler();
                    shButton.RunMusic("Res/button2.wav");
                    mp.shMenu.StopMusic();
                    shMulai = new SoundHandler();
                    shMulai.RunMusic("Res/play.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(LevelPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                GamePanel gp = new GamePanel();
                Game.cards.add(gp, "GamePanel");
                GamePanel.level = "medium";
                Game.cl.show(Game.cards, "GamePanel"); //show gamePanel when play is clicked
            }
            else if (me.getSource() == hard) {
                try {    
                    shButton = new SoundHandler();
                    shButton.RunMusic("Res/button2.wav");
                    mp.shMenu.StopMusic();
                    shMulai = new SoundHandler();
                    shMulai.RunMusic("Res/play.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(LevelPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                GamePanel gp = new GamePanel();
                Game.cards.add(gp, "GamePanel");
                GamePanel.level = "hard";
                Game.cl.show(Game.cards, "GamePanel"); //show gamePanel when play is clicked
            }
            else if (me.getSource() == back){
                Game.cl.show(Game.cards, "MenuPanel"); // show menuPanel when back button is clicked
                try {
                        shButton = new SoundHandler();
                        shButton.RunMusic("Res/button1.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //nambah if back disini ya bil
            
        }//end mouseClick
    }//end mouseAdapter

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //calling the super class functions
        Graphics2D g2d = (Graphics2D) g; //casting to graphcis2D
        setFocusable(true);		 //setting the focus on the panel

        g2d.drawImage(levelbkg, 0, 0, null); //draw menu image
        repaint();
    }//end paintComponent
//end GamePanel
}