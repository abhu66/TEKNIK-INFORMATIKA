/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abu.controller;

import abu.view.InputSiswa;
import java.sql.SQLException;

/**
 *
 * @author Abu Khoerul I A
 */
public interface Controller_InputData {
    public void Simpan(InputSiswa is);
    public void CariFoto(InputSiswa is);
    public void Kembali(InputSiswa is);
    public void Bersih(InputSiswa is);
    public void Update(InputSiswa is) throws SQLException;
    
}
