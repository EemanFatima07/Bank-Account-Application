/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
/**
 *
 * @author eeman
 */
package coe528.project;

public class Platinumlevel extends Account {

    // Method for performing an online purchase
    public void onlinePurchase(int amount) {
        // Check if the balance is sufficient for the purchase
        if (balance >= amount) {
            // Deduct the purchase amount from the balance
            balance -= amount;
            // Display a confirmation message for the purchase without additional service fee
            DisplayMessage.display("ONLINE PURCHASE CONFIRMATION", "You have made a purchase of $" + amount +
                    "\nwith no additional service fee.\n\nThe total cost is $" + amount + ".");
        } else {
            // Display a warning message if the balance is insufficient for the purchase
            DisplayMessage.display("WARNING", "Sorry, you don't have enough credit to make a purchase of $" + amount + ".");
        }
    }

    // Method to get the client level
    public String getLevel() {
        return "You're a PLATINUM Client!";  // displays the current level of the customer
    }
}
