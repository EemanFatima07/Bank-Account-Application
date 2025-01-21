/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
/**
 *
 * @author eeman
 */
package coe528.project;

public class Silverlevel extends Account {

    // Method for performing an online purchase
    public void onlinePurchase(int amount) {
        // Check if the balance is sufficient for the purchase including the additional service fee
        if (balance >= (amount + 20)) {
            // Deduct the total purchase cost (including service fee) from the balance
            balance -= (amount + 20);
            // Display a confirmation message for the online purchase with service fee
            DisplayMessage.display("ONLINE PURCHASE CONFIRMATION", "You have made a purchase of $" + amount +
                    "\nwith an additional service fee of $20.\n\nThe total cost is $" + (amount + 20) + ".");
        } else {
            // Display a warning message if the balance is insufficient for the purchase
            DisplayMessage.display("WARNING", "Sorry, you don't have enough credit to make a purchase of $" + amount +
                    "\nas the total cost is $" + (amount + 20) + ".");
        }
    }

    // Method to get the client level
    public String getLevel() {
        return "You're a SILVER Client!"; // displays the current level of the customer
    }
}
