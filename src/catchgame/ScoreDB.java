/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchgame;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ScoreDB{
    private Connection con;
    private String url;
    private String uname;
    private String pass;
    private Statement stmt;
    private String query;
    
    public ScoreDB(){
        
        url = "jdbc:mysql://localhost/projek";
        uname = "root";
        pass = "";
        this.setConnectionAndStatement();
    }
    
    private void setConnectionAndStatement(){
        try {
            con= DriverManager.getConnection(url, uname, pass);
            stmt = con.createStatement();//untuk memanfaatkan connection dalam membuat statemnt
            System.out.println("Koneksi berhasil");
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
            System.exit(1);
        }
    }

    /*public void save (User user, JLabel yourScore){
        try{
            query = "INSERT INTO catchgame VALUES (%d)";
            query = String.format(
                    query,
                    user.getScore());
            stmt.executeUpdate(query);//untuk memodifikasi tabel student (nambah variabel)
            System.out.println("Berhasil menambahkan data!");
        }catch (SQLException ex){
            System.err.print("Error Inserting data: " + ex.getMessage());
            System.exit(1);
        }
    }*/
    
    public void save (String nama, int score){
        try{
            query = "INSERT INTO catchgame VALUES ('%s', %d)";
            query = String.format(
                    query,
                    nama,
                    score);
            System.out.println(query);
            stmt.executeUpdate(query);//untuk memodifikasi tabel student (nambah variabel)
            System.out.println("Berhasil menambahkan data!");
        }catch (SQLException ex){
            System.err.print("Error Inserting data: " + ex.getMessage());
            System.exit(1);
        }
    }

    /*public Game get() {
        int score = 0;
        Game game = null;
        
        try{
            query = "SELECT * FROM catchgame ORDER BY score LIMIT 10";
            query = String.format(query, score);
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next() != false){
                game = new Game(
                            rs.getInt("score"));
            }else{
                System.out.println("Data Tidak Ditemukan.");
                System.exit(0);
            }
        }catch(SQLException ex){
            System.err.println("Error Getting The Data: " + ex.getMessage());
            System.exit(1);
        }
        return game;    
    }*/
    
    /*public ArrayList<User> getAll(){
        ArrayList<User> User = new ArrayList<>();
        
        try{
            query = "SELECT * FROM catchgame ORDER BY score LIMIT 10";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                allUser.add(new User(
                            rs.getString(1),
                            rs.getint(2)
            ));
            }
        }catch (SQLException ex){
            System.err.println("Error Getting The Data: " + ex.getMessage());
            System.exit(1);
        }
        return allGame;   

    }*/

}   
