/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import inputs.SoundHandler;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class MenuPanel extends JPanel {

    JButton play = new JButton("");
    JButton help = new JButton("");
    JButton exit = new JButton("");
//    JButton leaderboard = new JButton("");


    Image menubkg = new ImageIcon("images\\menumeong.jpeg").getImage();  //menu background

    /* Setting icons on buttons */
    ImageIcon playbtn = new ImageIcon("buttons\\play.png");
    ImageIcon helpbtn = new ImageIcon("buttons\\help.png");
    ImageIcon exitbtn = new ImageIcon("buttons\\EXIT (2).png");
//    ImageIcon lbbtn = new ImageIcon("buttons\\easy.png");
    SoundHandler shButton;
    SoundHandler shMenu;

    MenuPanel() {

        /* setting icons on buttons */
        play.setIcon(playbtn);
        help.setIcon(helpbtn);
        exit.setIcon(exitbtn);
//        leaderboard.setIcon(lbbtn);

        
        /* adding the buttons in the panel */
        this.add(play);
        this.add(help);
        this.add(exit);
//        this.add(leaderboard);
        
        setLayout(null);
        
        play.setBounds(351, 170, 180,70);
        help.setBounds(550, 170, 180,70);
        exit.setBounds(749, 170, 180,70);
//        leaderboard.setBounds(830, 170, 180, 70);
        
        /* adding mouseListeners on buttons */
        play.addMouseListener(new Click());
        help.addMouseListener(new Click());
        exit.addMouseListener(new Click());
//        leaderboard.addMouseListener(new Click());

        try {
            shMenu = new SoundHandler();
            shMenu.RunMusic("Res/menu.wav");
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end constructor

    class Click extends MouseAdapter { //internal friendly class

        public void mouseClicked(MouseEvent me) {
            if (me.getSource() == play) {
                Game.cl.show(Game.cards, "LevelPanel"); //show gamePanel when play is clicked
//                LevelPanel lp = new LevelPanel(this);
                try {
                   shButton = new SoundHandler();
                   shButton.RunMusic("Res/button1.wav");
                   //shMenu.StopMusic();
               } catch (LineUnavailableException ex) {
                   Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
               }               
//                int nama;
//                JFrame jFrame = new JFrame();
//                String getMessage = JOptionPane.showInputDialog("Masukkan Nama Anda");
//                jFrame.setName(getMessage);
            }
            else if (me.getSource() == help) {
                Game.cl.show(Game.cards, "HelpPanel"); //show helpPanel when help is clicked
                try {
                   shButton = new SoundHandler();
                   shButton.RunMusic("Res/button1.wav");
               } catch (LineUnavailableException ex) {
                   Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
            else if (me.getSource() == exit) {
                System.exit(0);  //exit application when exit is clicked
                try {
                   shButton = new SoundHandler();
                   shButton.RunMusic("Res/button1.wav");
               } catch (LineUnavailableException ex) {
                   Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
//            else if (me.getSource() == leaderboard) {
//                System.exit(0);  //exit application when exit is clicked
//                try {
//                   shButton = new SoundHandler();
//                   shButton.RunMusic("Res/button1.wav");
//               } catch (LineUnavailableException ex) {
//                   Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
//               }
//            }
        }//end mouseClick
    }//end mouseAdapter

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //calling the super class functions
        Graphics2D g2d = (Graphics2D) g; //casting to graphcis2D
        setFocusable(true);		 //setting the focus on the panel

        g2d.drawImage(menubkg, 0, 0, null); //draw menu image
        repaint();
    }//end paintComponent
//end GamePanel
}