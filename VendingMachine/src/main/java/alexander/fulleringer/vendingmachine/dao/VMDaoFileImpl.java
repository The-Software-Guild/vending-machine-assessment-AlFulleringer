/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package alexander.fulleringer.vendingmachine.dao;

import alexander.fulleringer.vendingmachine.dto.Item;
import alexander.fulleringer.vendingmachine.exceptions.DaoFileAccessException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class VMDaoFileImpl implements VMDao {
    private BigDecimal funds = new BigDecimal("0.00");
    private Map<String,Item> inventory = new HashMap<>();
    public static String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    
    
    public VMDaoFileImpl() throws DaoFileAccessException{
        this.readFile();
    }

    public VMDaoFileImpl(String testtxt) throws DaoFileAccessException {
        INVENTORY_FILE = testtxt;
        this.readFile();
    }
    
    @Override
    public Item addItem(String itemId, Item item) {
        return inventory.put(itemId, item);
    }
    
    @Override
    public Item getItem(String itemId) {
        return this.inventory.get(itemId);
    }
    
//    Removing items from inventory not under scope of this project
//    @Override
//    public Item removeItem(String itemId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(inventory.values());
        
    }
    
    @Override
    public void decrementItemCount(String itemId) {
        Item toDecrement = inventory.get(itemId);
        if (toDecrement != null){
            toDecrement.setStock(toDecrement.getStock()-1);
        }
    }
    
    //Should perhaps return a copy of available funds
    @Override
    public BigDecimal getFunds() {
        return this.funds;
    }
    
    @Override
    public void writeFile() throws DaoFileAccessException {
        PrintWriter out;
        String toWrite;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        }
        catch (IOException ex) {
            throw new DaoFileAccessException("Data could not be saved :(");
        }
        
        
        for(Item item: this.getAllItems()){
            toWrite = getMarshallItemString(item);
            out.println(toWrite);
        }
        out.flush();
        out.close();}
    
    @Override
    public void readFile() throws DaoFileAccessException {
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        }
        catch(FileNotFoundException e){
            //Turn the generic exception into our sapecific type so we can keep io in viewer.
            throw new DaoFileAccessException("File " + INVENTORY_FILE + "Could not be found :(");
        }
        
        String currentLine;
        Item currentItem;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            inventory.put(currentItem.getName(),currentItem);
        }
        scanner.close();
    }
    
    @Override
    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }
    
    private String getMarshallItemString(Item item) {
        String s = "";
        s += item.getName();
        s += DELIMITER;
        s += item.getCost().toString();
        s += DELIMITER;
        s += item.getStock();
        return s;
    }
    
    private Item unmarshallItem(String currentLine) {
        String[] tokens = currentLine.split(DELIMITER, 3);
        Item unmarshalledItem = new Item(tokens[0]);
        unmarshalledItem.setCost(new BigDecimal(tokens[1]));
        unmarshalledItem.setStock(Integer.parseInt(tokens[2]));
        return unmarshalledItem;
    }
    
    
    
}
