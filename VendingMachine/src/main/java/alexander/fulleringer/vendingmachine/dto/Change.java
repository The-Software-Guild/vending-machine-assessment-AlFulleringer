/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package alexander.fulleringer.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Alex
 */
public class Change {
    //TODO Define Change behaviour.
    BigDecimal change;
    
    public Change(BigDecimal toReturn){
        this.change = toReturn;
    }
    
    public String getChange(){
        String s = "";
        int changeInCents = getInCents(this.change);
        int coinValue;
        //Enforce Ordering
        for (Coin coin : Coin.values()){
            s += coin.toString();
            s+= ": ";
            coinValue = getInCents(coin.getValue());           
            s += changeInCents/coinValue;
            
            
            changeInCents = changeInCents % coinValue;
            s += "\n";
        }
//        s+= "Quarters: ";
//        s+= i / 25;
//        i = i % 25;
//       
//        s+= ", Dimes: ";
//        s+= i/10;
//        i=i%10;
//        
//        s+= ", Nickels: ";
//        s+=i/5;
//        i=i%5;
//        
//        s+= ", Pennies: ";
//        s+=i;
//        
        return s;
    }
    private int getInCents(BigDecimal bigDecimal){
        return bigDecimal.movePointRight(2).intValue();
    }
    
    public enum Coin{
        //Largest to smallest required
        QUARTER("0.25"), DIME("0.10"), NICKEL("0.05"), PENNY("0.01") ;
        public BigDecimal value;
        private Coin(String s){
            this.value = new BigDecimal(s);
        }
        public BigDecimal getValue(){
            return this.value;
        }
    }
    
    
    
}
