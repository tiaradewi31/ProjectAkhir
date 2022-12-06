/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import java.awt.*;
import javax.swing.*;

public class Game extends JFrame {
    static MenuPanel mp = new MenuPanel();
	static HelpPanel hp = new HelpPanel();
	static GamePanel gp = new GamePanel();
	
	static CardLayout cl = new CardLayout();
	static JPanel cards = new JPanel(); // to contain the panels as cards
	
	Game(){
		
		cards.setLayout(cl);// setting the layout to cardlayout
		cards.add(mp, "MenuPanel");
		cards.add(hp, "HelpPanel");
		cards.add(gp, "GamePanel");
		cl.show(cards, "MenuPanel");
		add(cards); //adding the panel with cardlayout in JFrame
		
		setTitle("Catch The Eggs Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 700); //frame size
		setVisible(true);   //frame visibility	
	}//end constructor
}