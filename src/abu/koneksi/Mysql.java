/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abu.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Abu Khoerul I A
 */
public class Mysql {
    private static Connection kon;
    public static Connection getKoneksi(){
        if(kon == null){
            try{
                String url = "jdbc:mysql://localhost/db_ti";
                String username = "root";
                String passwprd = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                kon=DriverManager.getConnection(url,username,passwprd);
            }catch(SQLException x){
                JOptionPane.showMessageDialog(null,"Gagal Koneksi !!"+x.getMessage());
            }
        }
        return kon;
        
    }
    
}
