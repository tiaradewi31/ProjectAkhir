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
	static AboutPanel ap = new AboutPanel();
    static LevelPanel lp = new LevelPanel(mp);
    static GamePanelEasy gpe = new GamePanelEasy();
    static GamePanelMedium gpm = new GamePanelMedium();
    static GamePanelHard gph = new GamePanelHard();
    static ScorePanel sp = new ScorePanel ();

    static CardLayout cl = new CardLayout();
    static JPanel cards = new JPanel(); // to contain the panels as cards
	
    public Game() {
        cards.setLayout(cl);// setting the layout to cardlayout
	cards.add(mp, "MenuPanel");
	cards.add(hp, "HelpPanel");
	cards.add(ap, "AboutPanel");
	cards.add(sp, "ScorePanel");
	cards.add(gpe, "GamePanelEasy");
	cards.add(gpm, "GamePanelMedium");
	cards.add(gph, "GamePanelHard");
	cards.add(lp, "LevelPanel");
	cl.show(cards, "MenuPanel");
	add(cards); //adding the panel with cardlayout in JFrame
		
	setTitle("Hungry Cat");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//agar gframe biisa di exit
	setSize(1280, 720); //frame size
	setVisible(true);
	setResizable(false); //frame visibility    }
    }
}