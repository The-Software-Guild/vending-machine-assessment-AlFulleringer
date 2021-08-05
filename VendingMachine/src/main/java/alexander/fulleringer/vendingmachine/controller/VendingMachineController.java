/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package alexander.fulleringer.vendingmachine.controller;

import alexander.fulleringer.vendingmachine.dao.VMDaoAuditorFileImpl;
import alexander.fulleringer.vendingmachine.dao.VMDaoFileImpl;
import alexander.fulleringer.vendingmachine.dto.Change;
import alexander.fulleringer.vendingmachine.dto.Change.Coin;
import alexander.fulleringer.vendingmachine.exceptions.AuditorFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.InsufficientFundsException;
import alexander.fulleringer.vendingmachine.exceptions.NoInventoryException;
import alexander.fulleringer.vendingmachine.service.VMService;
import alexander.fulleringer.vendingmachine.service.VMServiceImpl;
import alexander.fulleringer.vendingmachine.ui.UserIO;
import alexander.fulleringer.vendingmachine.ui.UserIOConsoleImpl;
import alexander.fulleringer.vendingmachine.ui.VMView;

/**
 *
 * @author Alex
 */
public class VendingMachineController {
    private VMService service;
    private VMView view;
    
    public VendingMachineController(){
        service = new VMServiceImpl(new VMDaoFileImpl(), new VMDaoAuditorFileImpl());
        UserIO io = new UserIOConsoleImpl();
        view = new VMView(io);
    }
    
    public VendingMachineController(VMService service, VMView view){
        this.service = service;
        this.view = view;
    }
    
    public void run(){
        boolean loop = true;
        int choice;
        while (loop){
            choice = view.printMenuGetSelection();
            
            switch (choice) {
                case 1:
                    addFunds();
                    break;
                case 2:
                    
                    purchaseItem();
                    break;
                case 3:
                    
                    returnChange();
                    break;
                case 4:
                    
                    showItemInventory();
                    break;
                case 5:
                    loop = false;
                    break;
                default:
                    view.displayUnkownCommandBanner();
            }
        }
    }
    
    private void addFunds() {
        boolean loop = true;
        Coin myCoin;
        int choice;
        
        while (loop){
            choice = view.displayAddFundsMenuGetSelection();
            try{
                switch (choice){
                    case 1:
                        myCoin = Coin.PENNY;
                        service.addFunds(myCoin);
                        break;
                    case 2:
                        myCoin = Coin.DIME;
                        service.addFunds(myCoin);
                        break;
                    case 3:
                        myCoin = Coin.NICKEL;
                        service.addFunds(myCoin);
                        break;
                    case 4:
                        myCoin = Coin.QUARTER;
                        service.addFunds(myCoin);
                        break;
                    case 5:
                        loop = false;
                        break;
                    default:
                        break;
                }
            }
            catch(AuditorFileAccessException e){
                view.displayError(e);
            }
            
            
        }
    }
    
    private void purchaseItem() {
        showItemInventory();
        String itemId;
        itemId = view.getItemSelection(service.getInventory());
        try{
            service.purchaseItem(itemId);
        }
            catch(InsufficientFundsException|NoInventoryException|AuditorFileAccessException e){
            view.printErrorMessage(e);
        }
    }
    
    private void returnChange() {
        String changeDescription;
        try{
            changeDescription = service.returnChange();
            view.printChange(changeDescription);
        }
        catch(AuditorFileAccessException e){
            view.printErrorMessage(e);
        }
    }
    
    private void showItemInventory() {
        view.displayInventory(service.getInventory());
    }
    
    
    
}
