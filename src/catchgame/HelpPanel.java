/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HelpPanel extends JPanel {
    Image helpbkg = new ImageIcon("images\\helpbkg.png").getImage(); //help image background
	JButton back = new JButton("Back"); //back button
	
	HelpPanel(){
		setFocusable(true); //setting the focus
		add(back);			//adding back button in the panel
		
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
						Game.cl.show(Game.cards, "MenuPanel"); // show menuPanel when back button is clicked
			}	
		  });
	}//end constructor
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(helpbkg, 0,0, null); // draw help background
		repaint();
	}//end paintComponent
}//end class

