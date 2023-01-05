/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import static com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager.getMessage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class User {
    private String nama;
    private int score;

    public User(String nama, int score) {
        this.nama = nama;
        this.score = score;
    }

    public String getNama() {
        return nama;
    }

//    public String setNama(String nama) {
//        this.nama = nama;
//    }

    public int getScore() {
        return score;
    }

    String setNama() {
        return nama;
    }
    
}
