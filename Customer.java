/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
/**
 *
 * @author eeman
 */
package coe528.project;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
        
public class Customer {
    // Declaring the instance variables
    private String username;
    private String password;
    private Account level;

    // Constructor
    public Customer(String username, String password) {
        try {
            // Create a new file with the username if it doesn't exist
            File loginInfo = new File(username + ".txt");
            if (loginInfo.createNewFile() == true) {
                // Print the provided user information to the console
                System.out.println("Provided user: " + loginInfo.getName());
                // Initialize username, password, and account level (set to Silver by default)
                this.username = username;
                this.password = password;
                level = new Silverlevel();
                // Write user information and initial balance to the file
                FileWriter writeFile = new FileWriter(username + ".txt");
                writeFile.write(username + "\n");
                writeFile.write(password + "\n");
                writeFile.write("" + 100); // Initial balance set to $100
                writeFile.close();
            } else if (loginInfo.exists()) {
                // If the file exists, set username, password, and account level (set to Silver by default)
                this.username = username;
                this.password = password;
                level = new Silverlevel();
            } else if (loginInfo.getName().equals(username)) {
                // If the file name matches the username, display an error message
                DisplayMessage.display("ERROR", "Username already taken! Please try again.");
            }
        } catch (IOException e) {
            // Handle IOException
            System.out.println("IOException error occurred!");
        }
    }

    // Getter method for getting the account balance
    public int getBalance() {
        return level.getBalance();
    }

    // Getter method for getting the username
    public String getUsername() {
        return username;
    }

    // Getter method for getting the password
    public String getPassword() {
        return password;
    }

    // Getter method for getting the account level
    public String getLevel() {
        return level.getLevel();
    }

    // Setter method for setting the account level based on the balance
    public void setLevel() {
        int balance = level.getBalance();

        // Determine the account level based on the balance
        switch (balance / 10000) {
            case 0: // If balance is less than $10000
                level = new Silverlevel();
                break;
            case 1: // If balance is exactly $10000 or more but less than $20000
                level = new Goldlevel();
                break;
            default: // If balance is more than $20000
                level = new Platinumlevel();
                break;
        }
        level.setBalance(balance);
    }

    // Method for depositing money into the account
    public void depositMoney(int amount) {
        // Add the deposited amount to the account balance
        level.addAmount(amount);
        // Display a deposit confirmation message
        DisplayMessage.display("DEPOSIT", "$" + amount + " has been deposited for your account.");
        // Update the account level based on the new balance
        this.setLevel();
    }

    // Method for withdrawing money from the account
    public void withdrawMoney(int amount) {
        // Check if the account balance is sufficient for withdrawal
        if (level.getBalance() >= amount) {
            // Subtract the withdrawn amount from the account balance
            level.removeAmount(amount);
            // Display a withdrawal confirmation message
            DisplayMessage.display("WITHDRAWAL", "$" + amount + " has been withdrawn from your account.");
            // Update the account level based on the new balance
            this.setLevel();
        } else {
            // Display a warning message if the account balance is insufficient for withdrawal
            DisplayMessage.display("WARNING", "Sorry, you do not have enough credit to withdraw $" + amount + ".");
        }
    }

    // Method for making an online purchase
    public void onlinePurchase(int amount) {
        // Check if the purchase amount meets the minimum requirement
        if (amount < 50) {
            // Display an error message if the purchase amount is below $50
            DisplayMessage.display("ERROR", "Online purchase must be $50 or more!");
            return;
        }
        // Perform the online purchase
        level.onlinePurchase(amount);
        // Update the account level based on the new balance
        this.setLevel();
    }
}

