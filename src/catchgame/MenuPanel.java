/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPanel extends JPanel {

    JButton play = new JButton("");
    JButton help = new JButton("");
    JButton exit = new JButton("");

    Image menubkg = new ImageIcon("images\\menumeong.jpeg").getImage();  //menu background

    /* Setting icons on buttons */
    ImageIcon playbtn = new ImageIcon("buttons\\play.png");
    ImageIcon helpbtn = new ImageIcon("buttons\\help.png");
    ImageIcon exitbtn = new ImageIcon("buttons\\EXIT (2).png");

    MenuPanel() {

        /* setting icons on buttons */
        play.setIcon(playbtn);
        help.setIcon(helpbtn);
        exit.setIcon(exitbtn);

        /* adding the buttons in the panel */
        add(play);
        add(help);
        add(exit);

        /* adding mouseListeners on buttons */
        play.addMouseListener(new Click());
        help.addMouseListener(new Click());
        exit.addMouseListener(new Click());

    }//end constructor

    class Click extends MouseAdapter { //internal friendly class

        public void mouseClicked(MouseEvent me) {
            if (me.getSource() == play) {
                Game.cl.show(Game.cards, "LevelPanel"); //show gamePanel when play is clicked
            }
            if (me.getSource() == help) {
                Game.cl.show(Game.cards, "HelpPanel"); //show helpPanel when help is clicked
            }
            if (me.getSource() == exit) {
                System.exit(0);  //exit application when exit is clicked
            }
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
