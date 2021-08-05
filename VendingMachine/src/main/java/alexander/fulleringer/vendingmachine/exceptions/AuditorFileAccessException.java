/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.vendingmachine.exceptions;

/**
 *
 * @author Alex
 */
public class AuditorFileAccessException extends Exception{
    public AuditorFileAccessException(String message){
        super(message);
    }
    public AuditorFileAccessException(String message, Throwable cause){
        super(message,cause);
    }
}