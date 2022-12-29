/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;

import javax.swing.JLabel;

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

    public int getScore() {
        return score;
    }

    
}
