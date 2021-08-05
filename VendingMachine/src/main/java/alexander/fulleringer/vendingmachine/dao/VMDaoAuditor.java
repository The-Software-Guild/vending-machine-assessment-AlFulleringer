/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.vendingmachine.dao;

import alexander.fulleringer.vendingmachine.dto.Change.Coin;
import alexander.fulleringer.vendingmachine.dto.Item;
import alexander.fulleringer.vendingmachine.exceptions.AuditorFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.DaoFileAccessException;
import java.math.BigDecimal;

/**
 *
 * @author Alex
 */
public interface VMDaoAuditor{
    void writeEntry(String s)throws AuditorFileAccessException;
}
