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


public class AboutPanel extends JPanel {

    Image aboutbkg = new ImageIcon("images\\about.png").getImage();
    JButton back = new JButton(""); //back button

    ImageIcon backbtn = new ImageIcon("buttons\\back.png");
    
    SoundHandler shButton;

    AboutPanel() {
        setFocusable(true); //setting the focus
        
        setLayout(null);
        back.setIcon(backbtn);
        this.add(back); //adding back button in the panel
        back.setBounds(550, 360, 180,70);//(x,y,width,height)
        
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                Game.cl.show(Game.cards, "MenuPanel"); // show menuPanel when back button is clicked
                try {
                        shButton = new SoundHandler();
                        shButton.RunMusic("Res/button1.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(AboutPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//end constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(aboutbkg, 0, 0, null); // draw help background
        repaint();
    }//end paintComponent
}//end class


