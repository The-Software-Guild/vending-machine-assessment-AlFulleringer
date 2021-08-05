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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Alex
 */
public class VMDaoTest {
    
    VMDao dao;
    Item testItem;
    
    public VMDaoTest() {
        
    }
    @BeforeEach
    public void setUp() {
        try{
            this.dao = new VMDaoFileImpl("test.txt");
            testItem = new Item("test");
            testItem.setCost(new BigDecimal("1.00"));
            testItem.setStock(1);
        }
        catch(DaoFileAccessException e){
            fail("Should be able to access file");
        }
    }
    /**
     * Test of addItem method, of class VMDao.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        
        dao.addItem(this.testItem.getName(), testItem);
       
        assertTrue(dao.getAllItems().contains(testItem)); 
//      Item expResult = new Item("test");
//      expResult.setCost(new BigDecimal("1.00"));
//      expResult.setStock(1);
    }
    
    /**
     * Test of getItem method, of class VMDao.
     */
    @Test
    public void testGetItem() {
        System.out.println("testGetItem");
        dao.addItem(testItem.getName(), testItem);
        
        assertEquals(testItem, dao.getItem(testItem.getName()));
    }
    
    /**
     * Test of getAllItems method, of class VMDao.
     * Presumes adding items works properly.
     */
    @Test
    public void testGetAllItems() {
        System.out.println("getAllItems");
        List<Item> inventory = dao.getAllItems();
        assertEquals(inventory.size(), 3);
        dao.addItem(this.testItem.getName(), testItem);
        inventory = dao.getAllItems();
        assertEquals(inventory.size(), 4);
    }
    
    /**
     * Test of decrementItemCount method, of class VMDao.
     */
    @Test
    public void testDecrementItemCount() {
        dao.addItem(this.testItem.getName(), testItem);
        dao.decrementItemCount(this.testItem.getName()); 
    }
    
    
//automated testing of getters and setters seems unnecessary
    /**
     * Test of getFunds method, of class VMDao.
     */
//    @Test
//    public void testGetFunds() {
//        System.out.println("getFunds");
//        VMDao instance = new VMDaoImpl();
//        BigDecimal expResult = null;
//        BigDecimal result = instance.getFunds();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    
    /**
     * Test of setFunds method, of class VMDao.
     */
//    @Test
//    public void testSetFunds() {
//        System.out.println("setFunds");
//        BigDecimal add = new BigDecimal("1.11");
//        dao.setFunds(add);
//        // TODO review the generated test code and remove the default call to fail.
//        assertTrue(add.equals(dao.getFunds()));
//    }
    
}
