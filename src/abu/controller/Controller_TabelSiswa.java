/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abu.controller;

import java.sql.SQLException;
import abu.view.FormDataSiswa;

/**
 *
 * @author Abu Khoerul I A
 */
public interface Controller_TabelSiswa {
    public void TampilSiswa(FormDataSiswa ds)throws SQLException;
    public void Edit(FormDataSiswa ds);
    public void Kembali(FormDataSiswa ds);
    public void Hapus(FormDataSiswa ds) throws  SQLException;
    public void NoBaris(FormDataSiswa ds);
    public void OnKlik(FormDataSiswa ds)throws  SQLException;
    public void CariData(FormDataSiswa ds) throws  SQLException;
    
}
