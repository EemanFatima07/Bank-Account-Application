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

public class Manager {
    // Declaring the instance variables
    private final String username = "admin";
    private final String password = "admin";

    // Constructor
    public Manager() {
        try {
            // Create a file with the manager's username if it doesn't exist
            File loginInfo = new File(username + ".txt");
            if (loginInfo.createNewFile() == true) {
                // Write manager's username and password to the file
                FileWriter writeFile = new FileWriter(username + ".txt");
                writeFile.write(username + "\n");
                writeFile.write(password);
                writeFile.close();
            }
        } catch (IOException e) {
            // Handle IOException
            System.out.println("IOException occurred!");
        }
    }

    // Method to add a new customer
    public Customer addCustomer(String username, String password) {
        // Create and return a new Customer object with the provided username and password
        return new Customer(username, password);
    }

    // Method to delete a customer
    public void deleteCustomer(Customer userToDelete) {
        // Create a file object corresponding to the customer's username
        File file = new File(userToDelete.getUsername() + ".txt");
        // If the file is deleted successfully, display a confirmation message
        if (file.delete()) {
            DisplayMessage.display("CONFIRMATION", "File of user successfully deleted!");
        }
    }
}
