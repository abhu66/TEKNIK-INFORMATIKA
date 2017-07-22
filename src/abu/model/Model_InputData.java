/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abu.model;

import abu.controller.Controller_InputData;
import abu.koneksi.Mysql;
import abu.view.FormDataSiswa;
import abu.view.InputSiswa;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Abu Khoerul I A
 */
public class Model_InputData implements Controller_InputData{
    File file;

    @Override
    public void Simpan(InputSiswa is) {
         //To change body of generated methods, choose Tools | Templates.
         if(is.jButton3.getText().equalsIgnoreCase("Update")){
             try {
                 Update(is);
             } catch (SQLException ex) {
                 Logger.getLogger(Model_InputData.class.getName()).log(Level.SEVERE, null, ex);
             }
         }else{
         if(is.jTextField1.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"NPM harus diisi !!");
             is.jTextField1.requestFocus();
         }else if(is.jTextField2.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"NAMA harus diisi !!");
             is.jTextField2.requestFocus();
         }else if(is.jTextField3.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"NO HP harus diisi !!");
             is.jTextField3.requestFocus();
         }else if(is.jTextField4.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"EMAIL harus diisi !!");
             is.jTextField4.requestFocus();
         }else if(is.jTextArea1.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"ALAMAT harus diisi !!");
             is.jTextArea1.requestFocus();
         }else if((!(is.jRadioButton1.isSelected())) && (!(is.jRadioButton2.isSelected()))){
             JOptionPane.showMessageDialog(null,"JENIS KELAMIN harus dipilih !!");
         }else{
             try{
                 String jk;
                 if(is.jRadioButton1.isSelected()){
                     jk = "L";
                 }else{
                     jk = "P";
                 }
                 Connection con = Mysql.getKoneksi();
                 String sql = "INSERT INTO siswa values(?,?,?,?,?,?,?)";
                 PreparedStatement ps= con.prepareStatement(sql);
                 ps.setString(1, is.jTextField1.getText());
                 ps.setString(2, is.jTextField2.getText().toUpperCase());
                 ps.setString(3, jk);
                 ps.setString(4, is.jTextField3.getText());
                 ps.setString(5, is.jTextField4.getText().toUpperCase());
                 ps.setString(6, is.jTextArea1.getText().toUpperCase());
                 ps.setString(7, is.jTextField5.getText());
                 ps.executeUpdate();
                 is.setAlwaysOnTop(false);
                 
                 JOptionPane.showMessageDialog(null,"Simpan Sukses !");
                 is.setAlwaysOnTop(true);
                 try{
                      String path= new File(".").getCanonicalPath();
                       FileUtils.copyFileToDirectory(file, new File(path+"/images")); //copy file ke folder image
                     } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Simpn Gambar gagal !"+ex.getMessage());
                     }
                 ps.close();
                 Bersih(is);
             }catch(SQLException x){
                 JOptionPane.showMessageDialog(null,"Simpn Gambar gagal !"+x.getMessage());
             }
         }
         }
    }

    @Override
    public void CariFoto(InputSiswa is) {
       //To change body of generated methods, choose Tools | Templates.
       JFileChooser jfc=new JFileChooser();
        if(jfc.showOpenDialog(is.jTextField5)== JFileChooser.APPROVE_OPTION){
            Toolkit tulkit = Toolkit.getDefaultToolkit();
            Image gambar = tulkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image resize = gambar.getScaledInstance(129, 177, Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(resize);
            is.jLabel9.setText("");
            is.jLabel9.setIcon(icon);
            is.jTextField5.setText(jfc.getSelectedFile().getName());
            
             file = new File(jfc.getSelectedFile().getPath());
        }
    }

    @Override
    public void Kembali(InputSiswa is) {
       is.dispose(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Bersih(InputSiswa is) {
        //To change body of generated methods, choose Tools | Templates.
        is.jTextArea1.setText("");
        is.jTextField1.setText("");
        is.jTextField2.setText("");
        is.jTextField3.setText("");
        is.jTextField4.setText("");
        is.jTextField5.setText("");
        is.jRadioButton1.setSelected(false);
        is.jRadioButton2.setSelected(false);
        is.jLabel9.setIcon(null);
    }
    @Override
    public void Update(InputSiswa is) throws SQLException{
        if(is.jTextField1.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"NPM harus diisi !!");
             is.jTextField1.requestFocus();
         }else if(is.jTextField2.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"NAMA harus diisi !!");
             is.jTextField2.requestFocus();
         }else if(is.jTextField3.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"NO HP harus diisi !!");
             is.jTextField3.requestFocus();
         }else if(is.jTextField4.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"EMAIL harus diisi !!");
             is.jTextField4.requestFocus();
         }else if(is.jTextArea1.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"ALAMAT harus diisi !!");
             is.jTextArea1.requestFocus();
         }else if((!(is.jRadioButton1.isSelected())) && (!(is.jRadioButton2.isSelected()))){
             JOptionPane.showMessageDialog(null,"JENIS KELAMIN harus dipilih !!");
         }else{
             try{
                 String jk;
                 if(is.jRadioButton1.isSelected()){
                     jk = "L";
                 }else{
                     jk = "P";
                 }
                  Connection con = Mysql.getKoneksi();
                 String sql = "UPDATE siswa set nama = ?, jk = ?, hp = ?, email = ?, alamat = ?, foto = ? where npm = ?";
                 PreparedStatement ps= con.prepareStatement(sql);
                 ps.setString(1, is.jTextField2.getText().toUpperCase());
                 ps.setString(2, jk);
                 ps.setString(3, is.jTextField3.getText());
                 ps.setString(4, is.jTextField4.getText().toUpperCase());
                 ps.setString(5, is.jTextArea1.getText().toUpperCase());
                 ps.setString(6, is.jTextField5.getText());
                 ps.setString(7, is.jTextField1.getText());
                 ps.executeUpdate();
                 is.setAlwaysOnTop(false);
                 JOptionPane.showMessageDialog(null,"Update Sukses !");
                 is.dispose();
                 try{
                      String path= new File(".").getCanonicalPath();
                       FileUtils.copyFileToDirectory(file, new File(path+"/images")); //copy file ke folder image
                     } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Simpn Gambar gagal !"+ex.getMessage());
                     }
                 ps.close();
                 Bersih(is);
             }catch(SQLException x){
                 JOptionPane.showMessageDialog(null,"Simpn Gambar gagal !"+x.getMessage());
             }
             finally{
                 FormDataSiswa ds = new FormDataSiswa();
                 ds.setVisible(true);
                 ds.setAlwaysOnTop(true);
             }
                 
         }
        
    }
    
}
