/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.vendingmachine.dao;

import alexander.fulleringer.vendingmachine.dto.Change;
import alexander.fulleringer.vendingmachine.dto.Item;
import alexander.fulleringer.vendingmachine.exceptions.AuditorFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.DaoFileAccessException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Alex
 */
public class VMDaoAuditorFileImpl implements VMDaoAuditor{
public static final String AUDIT_FILE = "audit.txt";
   
    public void writeEntry(String entry) throws AuditorFileAccessException {
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new AuditorFileAccessException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }

}
