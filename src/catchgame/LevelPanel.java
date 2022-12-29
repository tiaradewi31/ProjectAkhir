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

    Image levelbkg = new ImageIcon("images\\menulevel.png").getImage();  //level background

    /* Setting icons on buttons */
    ImageIcon easybtn = new ImageIcon("buttons\\easy.png");
    ImageIcon mediumbtn = new ImageIcon("buttons\\medium.png");
    ImageIcon hardbtn = new ImageIcon("buttons\\hard.png");

    JPanel center = new JPanel(); //adding another jpanel in a panel for boxLayout
    SoundHandler shButton;
    MenuPanel mp;

    LevelPanel(MenuPanel mp){
    //LevelPanel() {
        this.mp = mp;
//        mp.shMenu.StopMusic();

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));//setting box layout
        add(center); //adding the panel to another JPanel

        /* setting icons on buttons */
        easy.setIcon(easybtn);
        medium.setIcon(mediumbtn);
        hard.setIcon(hardbtn);

        /* adding the buttons in the panel */
        center.add(easy);
        center.add(medium);
        center.add(hard);

        /* adding mouseListeners on buttons */
        easy.addMouseListener(new Click());
        medium.addMouseListener(new Click());
        hard.addMouseListener(new Click());

    }//end constructor

    class Click extends MouseAdapter { //internal friendly class

        public void mouseClicked(MouseEvent me) {
            if (me.getSource() == easy) {
                try {    
                    shButton = new SoundHandler();
                    shButton.RunMusic("Res/button2.wav");
                    mp.shMenu.StopMusic();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(LevelPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                Game.cl.show(Game.cards, "GamePanelEasy"); //show gamePanel when play is clicked
//                try {
//                    SoundHandler.RunMusic("Res/play.wav");
//                } catch (LineUnavailableException ex) {
//                    Logger.getLogger(LevelPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
            else if (me.getSource() == medium) {
                try {    
                    shButton = new SoundHandler();
                    shButton.RunMusic("Res/button2.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(LevelPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                Game.cl.show(Game.cards, "GamePanelMedium"); //show gamePanel when play is clicked
            }
            else if (me.getSource() == hard) {
                try {    
                    //SoundHandler.RunMusic("Res/button2.wav");
                    shButton = new SoundHandler();
                    shButton.RunMusic("Res/button2.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(LevelPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                Game.cl.show(Game.cards, "GamePanelHard"); //show gamePanel when play is clicked
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