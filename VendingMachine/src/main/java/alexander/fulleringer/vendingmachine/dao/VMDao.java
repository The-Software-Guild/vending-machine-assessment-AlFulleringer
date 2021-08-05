                                                                                                                                                                           /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.vendingmachine.dao;

import alexander.fulleringer.vendingmachine.dto.Item;
import alexander.fulleringer.vendingmachine.exceptions.DaoFileAccessException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface VMDao {
    
    Item addItem(String itemId, Item item);
    Item getItem(String itemId);
    //Item removeItem(String itemId); Unnecessary, not altering which items are stocked
    List<Item> getAllItems();
    void decrementItemCount(String itemId);
    BigDecimal getFunds();
    
    void writeFile() throws DaoFileAccessException;
    void readFile() throws DaoFileAccessException;

    public void setFunds(BigDecimal add);
    
}
