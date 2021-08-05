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

/**
 *
 * @author Alex
 */
public class VMServiceImpl implements VMService {
    
    private VMDao dao;
    private VMDaoAuditor auditor;
    
    public VMServiceImpl(){
        try{
        this.dao = new VMDaoFileImpl();
        this.auditor = new VMDaoAuditorFileImpl();
        }
        catch(DaoFileAccessException e){
            System.out.println("This should not happen, use dependency injection!");
        }
        
    }
    public List<Item> getInventory(){
        return dao.getAllItems();
    }
    public BigDecimal getFunds(){
        return dao.getFunds();
    }
    public VMServiceImpl(VMDao dao, VMDaoAuditor auditor){
        this.dao = dao;
        this.auditor = auditor;
    }
    
    @Override
    public void purchaseItem(String itemId) throws InsufficientFundsException, NoInventoryException, AuditorFileAccessException, DaoFileAccessException {
        Item toPurchase = dao.getItem(itemId);
        if(toPurchase.getStock()>0){
            if(toPurchase.getCost().compareTo(dao.getFunds()) != 1){ // 1 means cost is greater than price
                dao.decrementItemCount(itemId);
                dao.setFunds(dao.getFunds().subtract(toPurchase.getCost()));
                auditor.writeEntry("Purchased " + itemId + "Funds are now: " + this.getFunds());
            }
            else{
                throw new InsufficientFundsException("You don't have the money to purchase that, you only have " + this.getFunds());
            }
        }
        else{
            throw new NoInventoryException("There are no "+ itemId + " remaining.");
        }
        
        
    }
    
    @Override
    public void addFunds(Coin myCoin) throws AuditorFileAccessException{
        this.dao.setFunds(dao.getFunds().add(myCoin.getValue()));
        this.auditor.writeEntry("Added funds: " + myCoin.toString() + "New total: " + this.dao.getFunds() );
        
    }
    
    @Override
    public void loadInventory() throws DaoFileAccessException {
        dao.readFile();    
    }
    
    @Override
    public void writeInventory() throws DaoFileAccessException {
        dao.writeFile();
    }

    @Override
    public String returnChange() throws AuditorFileAccessException{
        Change theChange = new Change(this.getFunds());
        auditor.writeEntry("Change returned: " + theChange.getAuditEntry());
        dao.setFunds(new BigDecimal("0.00"));
        return theChange.getChange();
    }
    
}
