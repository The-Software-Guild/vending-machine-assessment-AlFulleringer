/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package alexander.fulleringer.vendingmachine.ui;

import alexander.fulleringer.vendingmachine.dto.Change.Coin;
import alexander.fulleringer.vendingmachine.dto.Item;
import alexander.fulleringer.vendingmachine.exceptions.AuditorFileAccessException;
import java.util.List;

/**
 *
 * @author Alex
 */
public class VMView {
    private UserIO io;
    private final int NUM_MAIN_MENU_OPTIONS = 6;
    private final int NUM_COIN_MENU_OPTIONS = 5;
    
    public VMView(UserIO io){
        this.io=io;
    }
    
    public int printMenuGetSelection(){
        this.printMenu();
        return this.getMenuChoice(NUM_MAIN_MENU_OPTIONS);
    }
    /**
     * This function asks a user for input to fill out the required fields to make and return a new DVD
     *
     * @return
     */
//    public DVD getNewDVD(){
//        DVD newDVD = new DVD(io.readString("Please enter the DVD title"));
//
//        newDVD.setDirectorName(io.readString("Please enter the director's name"));
//        newDVD.setReleaseDate(io.readString("Please enter the release date"));
//        newDVD.setStudio(io.readString("Please enter the Studio name"));
//        newDVD.setMpaaRating(io.readString("Please enter the MPAA Rating"));
//        newDVD.setMiscInfo(io.readString("Please enter any additional information of note"));
//
//        return newDVD;
//    }
    public int displayAddFundsMenuGetSelection(){
        this.printAddFundsMenu();
        int choice = this.getMenuChoice(NUM_COIN_MENU_OPTIONS);
        return choice;
    }
    
    public void printMenu(){
        System.out.println("Welcome to your VM Application!");
        System.out.println("Your options are as follows:");
        
        System.out.println("1. Insert Coin(s)");
        System.out.println("2. Buy an item");
        System.out.println("3. Get your Change");
        System.out.println("4. See our Inventory");
        System.out.println("5. Leave the Vending Machine");
    }

//
    public void displayInventory(List<Item> inventory){
        io.print("Here is what we have in our Vending Machine!");
        for(Item item : inventory){
            displayItem(item);
        }
        
    }
    public void displayItem(Item item){
        if (item != null){
            io.print(item.toString());
        }
        else{
            io.print("That is not something we offer");
        }
    }
    
    public void displayDisplayAllBanner(){
        System.out.println("Here is our current stock!");
    }
    
    public int getMenuChoice(int numOptions){
        return io.readInt("Please select one of the above choices.",1,numOptions);
    }
    
//    public void displayFindDVDCompletion() {
//        io.readString("Press enter to continue\n");
//    }
//
//    public void displayDropDVDBanner() {
//        io.print("--- Drop a DVD ---");
//    }
    
    public void displayDropSuccessBanner() {
        io.readString("Please hit enter to continue.\n");
    }
    
    public void displayDisplayInventorySuccess() {
        io.readString("Inventory display complete.\nPlease hit enter to continue.\n");
    }
    
    public void displayDropResult(Item toDrop) {
        if (toDrop!=null){
            displayItem(toDrop);
            io.print("Item successfully removed from inventory");
        }
        else{
            io.print("No such item is in your inventory");
        }
        
        io.readString("Please press enter to continue");
    }
    
    
    public void printErrorMessage(Exception e){
        io.print("ERROR");
        io.print(e.getMessage());
    }
    
    public void displayGoodBye() {
        io.print("Good Bye!");
    }
    
    public void displayUnkownCommandBanner() {
        io.print("UNKNOWN COMMAND");
    }

    private void printAddFundsMenu() {
        System.out.println("Which coin would you like to add?");
        System.out.println("Your options are as follows:");
        
        System.out.println("1. Penny");
        System.out.println("2. Dime");
        System.out.println("3. Nickel");
        System.out.println("4. Quarter");
        System.out.println("5. Exit without inserting a coin");
    }

    public void displayError(AuditorFileAccessException e) {
        System.out.println("---ERROR---");
        System.out.println(e.getMessage());
              
    }

    public void printChange(String changeDescription) {
        System.out.println("Here is your change!");
        System.out.println(changeDescription);
    }

    public String getItemSelection(List<Item> inventory) {
        
        String itemId;
        int option=1;
        for (Item item : inventory){
            io.print("option " + option + " is: " + item.getName());
            option++;
        }
        int choice = io.readInt("Which # would you like?", 1, inventory.size());
        return inventory.get(choice-1).getName();
    }
    
    
}
