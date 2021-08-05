/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.vendingmachine.service;

import alexander.fulleringer.vendingmachine.dto.Change;
import alexander.fulleringer.vendingmachine.dto.Change.Coin;
import alexander.fulleringer.vendingmachine.exceptions.AuditorFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.DaoFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.InsufficientFundsException;
import alexander.fulleringer.vendingmachine.exceptions.NoInventoryException;
import java.math.BigDecimal;


/**
 *
 * @author Alex
 */
public interface VMService {

    /**
     *
     * @param itemId
     * @throws InsufficientFundsException
     */
    void purchaseItem(String itemId) throws InsufficientFundsException, NoInventoryException, AuditorFileAccessException;
    void addFunds(Coin myCoin) throws AuditorFileAccessException;
    void loadInventory() throws DaoFileAccessException;
    void writeInventory() throws DaoFileAccessException;
    BigDecimal getFunds();
    String returnChange() throws AuditorFileAccessException;
    
}
