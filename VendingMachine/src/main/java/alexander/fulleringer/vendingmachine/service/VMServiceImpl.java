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
public class VMServiceImpl implements VMService {
    
    private VMDao dao;
    private VMDaoAuditor auditor;
    
    public VMServiceImpl(){
        this.dao = new VMDaoFileImpl();
        this.auditor = new VMDaoAuditorFileImpl();
        
    }
    
    public BigDecimal getFunds(){
        return dao.getFunds();
    }
    public VMServiceImpl(VMDao dao, VMDaoAuditor auditor){
        this.dao = dao;
        this.auditor = auditor;
    }
    
    @Override
    public void purchaseItem(String itemId) throws InsufficientFundsException, NoInventoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void addFunds(Coin myCoin) throws AuditorFileAccessException{
        this.dao.setFunds(dao.getFunds().add(myCoin.getValue()));

        this.auditor.writeEntry("Added funds: " + myCoin.toString() + "New total: " + this.dao.getFunds() );
       
    }
    
    @Override
    public void loadInventory() throws DaoFileAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void writeInventory() throws DaoFileAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
