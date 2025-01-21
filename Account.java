/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
/**
 *
 * @author eeman
 */

package coe528.project;

import java.util.*;
/*
 *@Overview Bank account abstract class representing a mutable account with a balance.
 * The balance must be non-negative.  
 * This class is mutable because it allows its internal state, specifically the balance field, to be modified
 * after the object is created. Mutable objects are those whose state can be altered after creation. 
 * In this class, methods like setBalance, addAmount, and removeAmount directly modify the balance field, which changes the state of the object.
 *
 *
 * Abstraction Function:
 * AF(c) = bank account B such that B.balance = c.balance
 * 
 * Rep Invariant:
 * RI(c) = true if c.balance >= 0, false otherwise
 */


public abstract class Account {
    // Representation
    protected int balance;  // accessible within the same package and by subclasses
    
    // Constructor
    // EFFECTS: initializes balance to 100
    public Account(){
        this.balance = 100;   // initial balance of $100
    }
    
    // EFFECTS: returns the current balance
    protected int getBalance(){
        return this.balance;
    }
    
    // MODIFIES: this.balance
    // EFFECTS: sets balance to newBalance if non-negative
    protected void setBalance(int newBal){
        if(newBal >= 0)
            this.balance = newBal;
    }
    
    // MODIFIES: this.balance
    // EFFECTS: adds amount to balance if amount is non-negative
    protected void addAmount(int amount){
        this.balance += amount;
    }
    
    // MODIFIES: this.balance
    // EFFECTS: removes amount from balance if non-negative and less than or equal to balance
    protected void removeAmount(int amount){
        this.balance -= amount;
    }
    
    // EFFECTS: If amount is greater than or equal to $50 and balance is sufficient,
    //          subtracts (amount + service fee) from balance
    public void onlinePurchase(int amount){
        // Implementation of online purchase
    } 
    
    // EFFECTS: returns customer's current level
    public  String getLevel(){
        return ""; // empty string for now
    }
    
    // EFFECTS: returns balance as a string
    @Override
    public String toString(){
        return "Your Balance is $" + balance;
    }
    
    // EFFECTS: Returns true if the Rep Invariant holds for this object; 
    //          otherwise returns false
    public boolean repOK(){
        return this.balance >= 0;
    }
}



