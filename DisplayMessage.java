/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
/**
 *
 * @author eeman
 */
package coe528.project;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DisplayMessage {

    // Method to display a message window
    public static void display(String title, String message) {
        // Create a new stage for the message window
        Stage messageWindow = new Stage();

        // Block events to other windows until this one is closed
        messageWindow.initModality(Modality.APPLICATION_MODAL);
        messageWindow.setTitle(title);
        messageWindow.setMinWidth(450);

        // Create a label to display the message with specified font and text color
        Label lblMessage = new Label(message);
        lblMessage.setFont(Font.font("Monoid", 18));
        lblMessage.setTextFill(Color.DARKRED);

        // Create a button to close the message window
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> messageWindow.close());

        // Create a vertical box layout to arrange the label and button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblMessage, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        // Create a scene with the layout and set it in the message window
        Scene scene = new Scene(layout);
        messageWindow.setScene(scene);

        // Show the message window and wait for it to be closed
        messageWindow.showAndWait();
    }

}
