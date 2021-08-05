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
    
    public enum Coin{
        NO_COIN("0.00"), PENNY("0.01"), NICKEL("0.05"), DIME("0.10"), QUARTER("0.25");
        public BigDecimal value;
        private Coin(String s){
            this.value = new BigDecimal(s);
        }
        public BigDecimal getValue(){
            return this.value;
        }
    }
    
    
    
}
