/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import inputs.SoundHandler;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;


public class HelpPanel extends JPanel {

    Image helpbkg = new ImageIcon("images\\menuhelp.png").getImage(); //help image background
    JButton back = new JButton("Back"); //back button
    
    SoundHandler shButton;

    HelpPanel() {
        setFocusable(true); //setting the focus
        add(back);			//adding back button in the panel

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                Game.cl.show(Game.cards, "MenuPanel"); // show menuPanel when back button is clicked
                try {
//                    SoundHandler.RunMusic("Res/button1.wav");
                        shButton = new SoundHandler();
                        shButton.RunMusic("Res/button1.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//end constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(helpbkg, 0, 0, null); // draw help background
        repaint();
    }//end paintComponent
}//end class

