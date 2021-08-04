/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.vendingmachine.service;

import alexander.fulleringer.vendingmachine.exceptions.FileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.InsufficientFundsException;
import alexander.fulleringer.vendingmachine.exceptions.NoInventoryException;
import java.math.BigDecimal;


/**
 *
 * @author Alex
 */
public interface ServiceLayer {

    /**
     *
     * @param itemId
     * @throws InsufficientFundsException
     */
    void purchaseItem(String itemId) throws InsufficientFundsException, NoInventoryException;
    void addFunds(BigDecimal fund);
    void loadInventory() throws FileAccessException;
    void writeInventory() throws FileAccessException;
    
    
}
