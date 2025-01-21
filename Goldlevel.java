/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
/**
 *
 * @author eeman
 */

package coe528.project;

public class Goldlevel extends Account {

    // Method for performing an online purchase
    public void onlinePurchase(int amount){
        // Check if the balance is sufficient for the purchase including the service fee
        if(balance >= amount + 10) {
            // Deduct the purchase amount and the service fee from the balance
            balance -= (amount + 10);
            // Display a confirmation message with details of the purchase
            DisplayMessage.display("ONLINE PURCHASE CONFIRMATION", "You have made a purchase of $" + amount +
                    "\nwith an additional service fee of $10.\n\nThe total cost is $" + (amount + 10) + ".");
        }
        else {
            // Display a warning message if the balance is insufficient for the purchase
            DisplayMessage.display("WARNING", "Sorry, you don't have enough credit to make a purchase of $" + amount +
                    "\nas the total cost is $" + (amount + 10) + ".");
        }
    }

    // Method to get the client level
    public String getLevel(){
        return "You're a GOLD Client!"; // displays the current level of the customer
    }
}
