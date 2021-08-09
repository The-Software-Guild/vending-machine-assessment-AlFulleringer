/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.vendingmachine;

import alexander.fulleringer.vendingmachine.controller.VendingMachineController;
import alexander.fulleringer.vendingmachine.dao.VMDao;
import alexander.fulleringer.vendingmachine.dao.VMDaoAuditor;
import alexander.fulleringer.vendingmachine.dao.VMDaoAuditorFileImpl;
import alexander.fulleringer.vendingmachine.dao.VMDaoFileImpl;
import alexander.fulleringer.vendingmachine.exceptions.AuditorFileAccessException;
import alexander.fulleringer.vendingmachine.exceptions.DaoFileAccessException;
import alexander.fulleringer.vendingmachine.service.VMService;
import alexander.fulleringer.vendingmachine.service.VMServiceImpl;
import alexander.fulleringer.vendingmachine.ui.UserIO;
import alexander.fulleringer.vendingmachine.ui.UserIOConsoleImpl;
import alexander.fulleringer.vendingmachine.ui.VMView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author Alex
 */
public class App {
    public static void main(String[] args){
//        VMDao dao;
//        VMDaoAuditor auditor;
//        VMService service; 
//        UserIO io = new UserIOConsoleImpl();
//        VMView view = new VMView(io);
//        
//        try{
//            dao = new VMDaoFileImpl();
//            auditor = new VMDaoAuditorFileImpl();
//            service = new VMServiceImpl(dao,auditor);
//            VendingMachineController controller = new VendingMachineController(service, view);
//            controller.run();
//        }
//        catch(DaoFileAccessException e){
//            view.printErrorMessage(e);
//            System.exit(0);
//        }
//        
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        controller.run();
        
        
    }
}
