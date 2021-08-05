/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package alexander.fulleringer.vendingmachine.service;

import alexander.fulleringer.vendingmachine.dao.VMDao;
import alexander.fulleringer.vendingmachine.dao.VMDaoAuditor;
import alexander.fulleringer.vendingmachine.dao.VMDaoAuditorFileImpl;
import alexander.fulleringer.vendingmachine.dao.VMDaoFileImpl;
import alexander.fulleringer.vendingmachine.dto.Change;
import alexander.fulleringer.vendingmachine.dto.Change.Coin;
import alexander.fulleringer.vendingmachine.dto.Item;
import alexander.fulleringer.vendingmachine.exceptions.AuditorFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.DaoFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.InsufficientFundsException;
import alexander.fulleringer.vendingmachine.exceptions.NoInventoryException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alex
 */
public class VMServiceTest {
    
    VMService service;
    Item testItem;
    
    public VMServiceTest() {
    }
    
    
    @BeforeEach
    public void setUp() {
        
        testItem = new Item("test");
        testItem.setCost(new BigDecimal(".25"));
        testItem.setStock(1);
        try{
        VMDao dao = new VMDaoFileImpl();
        dao.addItem(testItem.getName(), testItem);
        VMDaoAuditor auditor = new VMDaoAuditorFileImpl("testAuditor.txt");
        service = new VMServiceImpl(dao, auditor);
        }
        catch(Exception e) {
        }
        
        
    }
    
    
    /**
     * Test of purchaseItem method, of class VMService.
     */
    @org.junit.jupiter.api.Test
    public void testPurchaseItemSuccess() throws Exception {
        System.out.println("purchaseItem");
        service.addFunds(Coin.QUARTER);
        try{
            service.purchaseItem(testItem.getName());
        }
        catch(Exception e){
            fail("No error should occur in this purchase");
        }
    }
    @org.junit.jupiter.api.Test
    public void testPurchaseItemStockFail() throws Exception {
        System.out.println("purchaseItemStockFail");
        service.addFunds(Coin.QUARTER);
        try{
            service.purchaseItem(testItem.getName());
        }
        catch(Exception e){
            fail("No error should occur in this purchase");
        }
        service.addFunds(Coin.QUARTER);
        try{
            service.purchaseItem(testItem.getName());
        }
        catch(NoInventoryException e){
            
        }
    }
    @org.junit.jupiter.api.Test
    public void testPurchaseItemFundsFail() throws Exception {
       try{
            service.purchaseItem(testItem.getName());
        }
        catch(InsufficientFundsException e){          
        }
    }
    /**
     * Test of addFunds method, of class VMService.
     */
    @org.junit.jupiter.api.Test
    public void testAddFunds() throws Exception {
        System.out.println("addFunds");
        Change.Coin myCoin = Coin.QUARTER;
        service.addFunds(myCoin);
        assertTrue(service.getFunds().compareTo(new BigDecimal("0.25")) == 0);
    }
    
        /**
     * Test of returnChange method, of class VMService.
     */
    @org.junit.jupiter.api.Test
    public void testReturnChange() throws Exception {
        service.addFunds(Coin.QUARTER);
        service.addFunds(Coin.DIME);
        
        String result = service.returnChange();
        String expResult = "QUARTER: 1\nDIME: 1\nNICKEL: 0\nPENNY: 0\n";
        
        assertEquals(expResult, result);
        assertTrue(service.getFunds().compareTo(new BigDecimal("0.00"))==0);
        // TODO review the generated test code and remove the default call to fail.
    }
    
//    /**
//     * Test of loadInventory method, of class VMService.
//     */
//    @org.junit.jupiter.api.Test
//    public void testLoadInventory() throws Exception {
//        System.out.println("loadInventory");
//        VMService instance = new VMServiceImpl();
//        instance.loadInventory();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    /**
//     * Test of writeInventory method, of class VMService.
//     */
//    @org.junit.jupiter.api.Test
//    public void testWriteInventory() throws Exception {
//        System.out.println("writeInventory");
//        VMService instance = new VMServiceImpl();
//        instance.writeInventory();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    /**
//     * Test of getFunds method, of class VMService.
//     */
//    @org.junit.jupiter.api.Test
//    public void testGetFunds() {
//        System.out.println("getFunds");
//        VMService instance = new VMServiceImpl();
//        BigDecimal expResult = null;
//        BigDecimal result = instance.getFunds();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    

    
    /**
     * Test of getInventory method, of class VMService.
     */
//    @org.junit.jupiter.api.Test
//    public void testGetInventory() {
//        System.out.println("getInventory");
//        VMService instance = new VMServiceImpl();
//        List<Item> expResult = null;
//        List<Item> result = instance.getInventory();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
