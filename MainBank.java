/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

package coe528.project;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class MainBank extends Application {
   
    private int index = -1; // the index is initialized at index -1
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private Customer tempUser;
    private static Manager manager = new Manager();
    
    // scenes
    private Scene loginScreen, managerScreen,customerScreen;
    
    // login button
    private Button loginBtn = new Button("LOGIN");
    
    // labels for login screen
    Label labelInfo0 = new Label("Welcome! Please proceed to login below to access our Banking System.\n");
    Label labelUser = new Label("Username: ");
    Label labelPass = new Label("Password: ");
    
            
    // username and password inputs for Login Screen
    TextField usernameInput = new TextField();
    PasswordField passwordInput = new PasswordField();
    

    
    // main method to launch the app
    public static void main(String[] args) {
        // storing and adding already existing text files to customers's arrayList     
        String currentFile = System.getProperty("user.dir"); 
        File dir = new File(currentFile);
        for(File file : dir.listFiles()){
            if(file.getName().endsWith((".txt"))){
                try(Scanner readFile = new Scanner(file)){
                    String u = readFile.next();
                    String pw = readFile.next();
                    int balance = Integer.parseInt(readFile.next());
                    customers.add(new Customer(u,pw));
                    for(Customer c : customers){
                        if(c.getUsername().equals(u)) {
                            c.depositMoney(balance - 100); 
                            c.setLevel(); }
                    }
                }
                catch(Exception e3){}  
            }
        }
         
        Application.launch(args);
        
    }

    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Banking Management System");

        // labels for customer screen
        Label dLbl = new Label("\n\nWhat amount are you looking to deposit?");
        Label wLbl = new Label("\nWhat amount do you wish to withdraw?");
        Label opLbl = new Label("\n\nTo buy a product online, please input its price:");
        
        // textfields for customer screen
        TextField depositMoney = new TextField();
        TextField withdrawMoney = new TextField();
        TextField onlinePurchase = new TextField();
         
        // buttons for customer screen
        Button getBalanceBtn = new Button("Check Balance");
        Button getCurrLevelBtn = new Button("Current Level");
        Button depositAction = new Button("Deposit");
        Button withdrawAction = new Button("Withdraw");
        Button onlinePurchaseAction = new Button("Finish Transaction");
        Button userLogoutBtn = new Button("LOGOUT");
        
        // labels for manager screen
        Label labelInfo1 = new Label("To add a new customer, kindly input their login credentials below.\n");
        Label labelNewUser = new Label("Input the new customer's username:");
        Label labelNewPass = new Label("Input the new customer's password:");
        Label labelDeleteUser = new Label("\n\nWhom would you like to remove from the system? Please provide the username.");
        
        // buttons and textfields for the Manager screen
        Button createCustomerBtn = new Button("Create Customer");
        Button deleteCustomerBtn = new Button("Delete Customer");
        Button managerLogoutBtn = new Button("LOGOUT");
        TextField createUsername = new TextField();
        TextField createPassword = new TextField();
        TextField userToDelete = new TextField();
        
        // setting actions to all buttons in this class
        loginBtn.setOnAction(e ->{ 
               String fileNameInputted = usernameInput.getText();
               String passInputted = passwordInput.getText();
               File f = new File(fileNameInputted + ".txt");
               if(f.exists()) { 
                    try(Scanner readFile = new Scanner(f)){
                        String userOnFile = readFile.next();
                        String passOnFile = readFile.next();

                        if(fileNameInputted.equals(userOnFile) && passInputted.equals(passOnFile)){
                            if(userOnFile.equals("admin") && passOnFile.equals("admin")){
                                primaryStage.setScene(managerScreen);
                                primaryStage.setTitle("Manager");
                                usernameInput.clear();
                                passwordInput.clear();
                            }

                            else {  // setting the temporary customer/user variable to the current customer in the list
                               for(int i = 0; i < customers.size(); i++){    
                                    if(customers.get(i).getUsername().equals(fileNameInputted)){
                                        tempUser = customers.get(i);
                                        index = i;
                                    }                        
                                }
                                primaryStage.setScene(customerScreen);
                                primaryStage.setTitle("Customer");
                                usernameInput.clear();
                                passwordInput.clear();
                            }  
                        }
                        else {
                            DisplayMessage.display("ERROR", "The username or password provided is incorrect. Please try again."); }
               
                    }  
                    catch(Exception e1){
                         System.out.println("Opps...an error occurred. Please try again.");
                        }            
               }
               else if(fileNameInputted.equals("") && passInputted.equals("")) {
                   DisplayMessage.display("ERROR", "No input provided.");
               }
               else{
                   DisplayMessage.display("ERROR", "Sorry, This user is not registered in our system.");  // the file doesn't exist in the bank app
                           
               }
                                           
        });
        
        
        userLogoutBtn.setOnAction(e -> { 
            try{
                FileWriter writeToFile = new FileWriter(tempUser.getUsername() + ".txt");
                writeToFile.write(tempUser.getUsername() + "\n");
                writeToFile.write(tempUser.getPassword() + "\n");
                writeToFile.write("" + tempUser.getBalance());
                writeToFile.close();
            }
            catch(Exception w){}
            
            primaryStage.setScene(loginScreen);
            primaryStage.setTitle("Banking Management System");
            // clearing all money inputted by the user on the screen when logging out
            depositMoney.clear();
            withdrawMoney.clear();
            onlinePurchase.clear(); 
        });

        
        managerLogoutBtn.setOnAction(e -> {
            primaryStage.setScene(loginScreen);
            primaryStage.setTitle("Banking Management System");
            // clearing all accounts inputted by the manager on the screen when logging out
            createUsername.clear();
            createPassword.clear();
            userToDelete.clear();
         
        });
         
        createCustomerBtn.setOnAction(e -> {
            Customer c = new Customer(createUsername.getText(),createPassword.getText());
            customers.add(c);
            createUsername.clear();
            createPassword.clear();
            DisplayMessage.display("NEW CUSTOMER", "Congratulations, a customer has been created!");

        });
        
        deleteCustomerBtn.setOnAction(e -> {
           String deleteThisUsername = userToDelete.getText();
           for(int i = 0; i < customers.size() ; i++){
               if(customers.get(i).getUsername().equals(deleteThisUsername)){
                   manager.deleteCustomer(customers.get(i));
                   customers.remove(i);
                   userToDelete.clear(); // removing the text from its field
                   break;
               }
           }          
        });
        
        getBalanceBtn.setOnAction(e ->{
            DisplayMessage.display("BALANCE", "Your Balance is $" + tempUser.getBalance() + ".");
        });
        
        
        getCurrLevelBtn.setOnAction(e -> {
            DisplayMessage.display("LEVEL", tempUser.getLevel());
        });
       
        
        // setting actions to textfields
        depositAction.setOnAction(e ->{
            
            try{
                int amount = Integer.parseInt(depositMoney.getText());
                tempUser.depositMoney(amount);
                depositMoney.clear();
            }
            catch(NumberFormatException e1){
                     DisplayMessage.display("ERROR", "Only integers can be entered!");  
                     depositMoney.clear();
            }      
        });
        
        withdrawAction.setOnAction(e -> {
            
            try{
                int amount = Integer.parseInt(withdrawMoney.getText());
                tempUser.withdrawMoney(amount);
                withdrawMoney.clear();
            }
            catch(NumberFormatException e1){
                     DisplayMessage.display("ERROR", "Only integers can be entered!");  
                     withdrawMoney.clear();
            }      
        });
        
        onlinePurchaseAction.setOnAction(e -> {
            
            try{
                int amount = Integer.parseInt(onlinePurchase.getText());
                tempUser.onlinePurchase(amount);
                onlinePurchase.clear();
            }
            catch(NumberFormatException e1){
                     DisplayMessage.display("ERROR", "Only integers can be entered!");
                     onlinePurchase.clear();
            }  
        });
        
      
                    
        // pane of login screen
        GridPane screen1 = new GridPane();
        screen1.setAlignment(Pos.TOP_LEFT);
        screen1.add(labelInfo0, 1, 0);
        screen1.add(labelUser, 0, 1);
        screen1.add(usernameInput, 1, 1);
        screen1.add(labelPass, 0, 2);
        screen1.add(passwordInput, 1, 2);
        screen1.add(loginBtn, 1, 3);
        

        // Center the login button horizontally
        GridPane.setHalignment(loginBtn, HPos.CENTER);

       // Make the login button bigger
       loginBtn.setStyle("-fx-font-size: 15px;");

       
        
        // spacing between each label, textfield and button
        screen1.setHgap(11);
        screen1.setVgap(11);
        
        // customized fonts and texts for labels
        labelInfo0.setFont(Font.font ("Monoid", 18));
        labelInfo0.setTextFill(Color.GREEN);
        labelUser.setFont(Font.font ("Monoid", 16));
        labelUser.setTextFill(Color.LIME);
        labelPass.setFont(Font.font ("Monoid", 16));
        labelPass.setTextFill(Color.LIME);
        
        // creates the size of the login screen
        loginScreen = new Scene(screen1,900,600);
       
        // pane of manager screen   
        GridPane screen2 = new GridPane();
        screen2.setAlignment(Pos.TOP_LEFT);
        
        // customized fonts and texts for labels and buttons
        screen2.add(labelInfo1, 0, 0);
        labelInfo1.setFont(Font.font ("Monoid", 18));
        labelInfo1.setTextFill(Color.GREEN);
        
        screen2.add(labelNewUser, 0, 1);
        labelNewUser.setFont(Font.font ("Monoid", 16));
        labelNewUser.setTextFill(Color.LIME);
        
        screen2.add(createUsername, 0, 2);
        createUsername.setFont(Font.font ("Monoid", 15));
     
        screen2.add(labelNewPass, 0, 3);
        labelNewPass.setFont(Font.font ("Monoid", 16));
        labelNewPass.setTextFill(Color.LIME);
        
        screen2.add(createPassword, 0, 4);
        createPassword.setFont(Font.font ("Monoid", 15));
        
        screen2.add(createCustomerBtn, 0, 5);
        createCustomerBtn.setFont(Font.font ("Monoid", 15));
        
        screen2.add(withdrawAction, 0, 6); 
        labelDeleteUser.setFont(Font.font ("Monoid", 18));
        labelDeleteUser.setTextFill(Color.LIME);
       
        screen2.add(labelDeleteUser, 0, 7);
        labelDeleteUser.setFont(Font.font ("Monoid", 16));
        labelDeleteUser.setTextFill(Color.LIME);

        screen2.add(userToDelete, 0, 8); 
        userToDelete.setFont(Font.font ("Monoid", 15));
        
        screen2.add(deleteCustomerBtn, 0, 9);
        deleteCustomerBtn.setFont(Font.font ("Monoid", 15));
        
        screen2.add(managerLogoutBtn, 1, 10);  // logout button for manager
        managerLogoutBtn.setPrefWidth(200); // Set the preferred width
        managerLogoutBtn.setPrefHeight(50); // Set the preferred height

        // spacing between each label, textfield and button
        screen2.setHgap(15);
        screen2.setVgap(15);
       
        // creates the size of the manager screen
        managerScreen = new Scene(screen2,1050,700);
        
        // pane of customer screen
        GridPane screen3 = new GridPane();
        screen3.setAlignment(Pos.TOP_LEFT);
        
        screen3.add(getBalanceBtn, 0, 0);
        screen3.add(getCurrLevelBtn, 1, 0);
        
        screen3.add(dLbl, 0, 1);
        dLbl.setFont(Font.font("Monoid", 16));
        dLbl.setTextFill(Color.LIME);
        screen3.add(depositMoney, 0, 2);
        screen3.add(depositAction, 0, 3);  // button for despositing
        
        screen3.add(wLbl, 0, 4);
        wLbl.setFont(Font.font("Monoid", 16));
        wLbl.setTextFill(Color.LIME);
        screen3.add(withdrawMoney, 0, 5);
        screen3.add(withdrawAction, 0, 6); // button for withdrawing
       
        screen3.add(opLbl, 0, 7);
        opLbl.setFont(Font.font("Monoid", 16));
        opLbl.setTextFill(Color.LIME);
        screen3.add(onlinePurchase, 0, 8); 
        screen3.add(onlinePurchaseAction, 0, 9); // button for purchasing online
        
        screen3.add(userLogoutBtn, 1, 10);  // logout button for customer
        // Set preferred width and height for Check Balance button
        getBalanceBtn.setPrefWidth(200);
        getBalanceBtn.setPrefHeight(50);

        // Set preferred width and height for Current Level button
        getCurrLevelBtn.setPrefWidth(200);
        getCurrLevelBtn.setPrefHeight(50);

        // Set preferred width and height for Deposit button
        depositAction.setPrefWidth(100);
        depositAction.setPrefHeight(30);

        // Set preferred width and height for Withdraw button
        withdrawAction.setPrefWidth(100);
        withdrawAction.setPrefHeight(30);

        // Set preferred width and height for Complete Transaction button
        onlinePurchaseAction.setPrefWidth(120);
        onlinePurchaseAction.setPrefHeight(30);

        // Set preferred width and height for Logout button
        userLogoutBtn.setPrefWidth(200);
        userLogoutBtn.setPrefHeight(50);
        
        // spacing between each label, textfield and button
        screen3.setHgap(10);
        screen3.setVgap(10);

        // creates the size of the customer screen
        customerScreen = new Scene(screen3,900,800);
        
        
        primaryStage.setScene(loginScreen);  // places the scene in the stage
        primaryStage.show(); // displays the stage
    }
}








