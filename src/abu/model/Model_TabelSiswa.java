/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abu.model;

import abu.controller.Controller_TabelSiswa;
import abu.koneksi.Mysql;
import abu.view.FormDataSiswa;
import abu.view.InputSiswa;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.swing.SwingAccessor;

/**
 *
 * @author Abu Khoerul I A
 */
public class Model_TabelSiswa implements Controller_TabelSiswa{

    @Override
    public void TampilSiswa(FormDataSiswa ds) throws SQLException{
        ds.tabelmodel.getDataVector().removeAllElements();
        ds.tabelmodel.fireTableDataChanged();
            Connection con = Mysql.getKoneksi();
            try{
            String sql = "SELECT * FROM siswa ORDER BY NAMA asc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object [] data  =  new Object[7];
                data[1] = rs.getString(1);
                data[2] = rs.getString(2);
                data[3] = rs.getString(3);
                data[4] = rs.getString(4);
                data[5] = rs.getString(5);
                data[6] = rs.getString(6);
                ds.tabelmodel.addRow(data); 
                NoBaris(ds);
            }
            }catch(SQLException x){
                JOptionPane.showMessageDialog(null,"Gagal Load data !!"+x.getMessage());
            }
    
    }

    @Override
    public void Edit(FormDataSiswa ds) {
        //To change body of generated methods, choose Tools | Templates.
        if(ds.jTable1.getSelectedRow() == -1){
            ds.getToolkit().beep();
            ds.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null,"Pilih Data !");
            ds.setAlwaysOnTop(true);
            
        }else{
            ds.dispose();
        int baris = ds.jTable1.getSelectedRow();
        InputSiswa is = new InputSiswa(ds,true);
        is.jTextField1.setText(ds.jTable1.getValueAt(baris, 1).toString());
        is.jTextField1.setEnabled(false);
        is.jTextField2.setText(ds.jTable1.getValueAt(baris, 2).toString());
        if(ds.jTable1.getValueAt(baris, 3).toString().equals("L")){
            is.jRadioButton1.setSelected(true);
        }else{
            is.jRadioButton2.setSelected(true);
        }
        is.jTextField3.setText(ds.jTable1.getValueAt(baris, 4).toString());
        is.jTextField4.setText(ds.jTable1.getValueAt(baris, 5).toString());
        is.jTextArea1.setText(ds.jTable1.getValueAt(baris, 6).toString());
        is.jTextField5.setText(ds.jLabel1.getText());
            if(is.jTextField5.getText().isEmpty()){
                is.jLabel9.setText("Image Not Found !!");
            }else{
        
        try{
                    Toolkit tulkit = Toolkit.getDefaultToolkit();
                    String path=new File(".").getCanonicalPath();
                    Image image=tulkit.getImage(path+"/images/"+is.jTextField5.getText()); //mengambil gambar dari folder image
                    Image imagedResized=image.getScaledInstance(123, 177, Image.SCALE_DEFAULT); //resize foto sesuai ukuran jlabel
                    ImageIcon icon=new ImageIcon(imagedResized);
                    is.jLabel9.setIcon(icon);
                    is.jLabel9.setText("");
                    }catch(IOException x){
                        
                    }
            }
        
        is.jButton3.setText("Update");
        is.setVisible(true);
        
        }
    }

    @Override
    public void Kembali(FormDataSiswa ds) {
        ds.dispose(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Hapus(FormDataSiswa ds) throws SQLException{
      //To change body of generated methods, choose Tools | Templates.
      int baris = ds.jTable1.getSelectedRow();
      Connection con = Mysql.getKoneksi();
      try{
      String sql = "DELETE FROM siswa where npm = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ds.jTable1.getValueAt(baris, 1).toString());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Hapus Sukses !");
        ps.close();
        TampilSiswa(ds);
      }catch(SQLException x){
          
      }
    }
    @Override
    public void NoBaris(FormDataSiswa ds){
        int baris = ds.jTable1.getRowCount();
        for(int x = 0; x<baris; x++){
            int nomor = x + 1;
            ds.jTable1.setValueAt(nomor + ".",x,0);
        }
    }
    @Override
    public void OnKlik(FormDataSiswa ds) throws SQLException{
        int baris = ds.jTable1.getSelectedRow();
        
            Connection con = Mysql.getKoneksi();
            String sql = "SELECT foto from siswa where npm = '"+ds.jTable1.getValueAt(baris, 1).toString()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object [] data = new Object[1];
                data[0] = rs.getString(1);
                ds.jLabel1.setText(data[0].toString());
            }
                if((ds.jLabel1.getText().isEmpty()) || (ds.jLabel1.getText() == null)){
                    ds.jLabel2.setVisible(true);
                    ds.jLabel2.setIcon(null);
                    ds.jLabel2.setText("Image Not Found !!");
                }else{
                    try{
                    Toolkit tulkit = Toolkit.getDefaultToolkit();
                    String path=new File(".").getCanonicalPath();
                    Image image=tulkit.getImage(path+"/images/"+ds.jLabel1.getText()); //mengambil gambar dari folder image
                    Image imagedResized=image.getScaledInstance(123, 177, Image.SCALE_DEFAULT); //resize foto sesuai ukuran jlabel
                    ImageIcon icon=new ImageIcon(imagedResized);
                    ds.jLabel2.setIcon(icon);
                    ds.jLabel2.setText("");
                    ds.jLabel1.setVisible(false);
                    
                    }catch(IOException x){
                        
                    }
                }
            
            
    }
    @Override
    public void CariData(FormDataSiswa ds) throws SQLException{
        if(ds.jTextField1.getText().isEmpty()){
            ds.getToolkit().beep();
            ds.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null, "Cari Berdasarkan NPM atau NAMA");
            ds.setAlwaysOnTop(true);
            ds.jTextField1.setText("NPM atau NAMA");
            ds.requestFocus();
        }else{
           ds.tabelmodel.getDataVector().removeAllElements();
            ds.tabelmodel.fireTableDataChanged();
            Connection con = Mysql.getKoneksi();
            try{
            String sql ="SELECT * FROM siswa where npm = '"+ds.jTextField1.getText()+"' or nama like '%"+ds.jTextField1.getText()+"%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
           
            
            while(rs.next()){
               
                Object [] data  =  new Object[7];
                data[1] = rs.getString(1);
                data[2] = rs.getString(2);
                data[3] = rs.getString(3);
                data[4] = rs.getString(4);
                data[5] = rs.getString(5);
                data[6] = rs.getString(6);
                ds.tabelmodel.addRow(data); 
                NoBaris(ds);
                
            }
            st.close();
            }catch(SQLException x){
                JOptionPane.showMessageDialog(null, "Gagal Load data !"+x.getMessage());
            }
            
        }
    }
    
    
}
