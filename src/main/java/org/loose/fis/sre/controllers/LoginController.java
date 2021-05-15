package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UserNotFoundException;
import org.loose.fis.sre.model.LoggedUser;
import org.loose.fis.sre.model.User;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Node;
import org.loose.fis.sre.services.UserService;
import java.util.Objects;

import java.net.*;
import java.io.*;

import java.nio.file.Paths;

import java.io.IOException;

public class LoginController {
    private Stage stage;
    private Parent root;

    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    public void openMainPageForRole(String resourceFilename, String titleStage, ActionEvent event) throws IOException { }

    @FXML
    public void handleLoginAction(ActionEvent event) {
        try {
            final User loggedUser = UserService.loginUser(usernameField.getText(), passwordField.getText());
            //loginMessage.setText("Login successful!");

            LoggedUser.setLoggedUser(loggedUser);
            if (loggedUser.getRole().equals("Admin")) {
                //openMainPageForRole("adminmain.fxml", "D&C Make-up Shop Admin Main Page", event);
                System.out.println("path" + getClass().getResource("adminmain.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("adminmain.fxml"));
                // Parent root1 =  FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/login.fxml"));
                Parent root = fxmlLoader.load();
            } else {
                openMainPageForRole("clientmain.fxml", "D&C Make-up Shop Client Main Page", event);
                //System.out.println("path" + getClass().getResource("clientmain.fxml"));
               // FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("clientmain.fxml"));
                // Parent root1 =  FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/login.fxml"));
                //Parent root = fxmlLoader.load();
            }

        } catch (UserNotFoundException e) {
            long noOfUsers = UserService.getNoOfUsers();
            loginMessage.setText("Number of users for debugging: " + noOfUsers);
        } catch (IOException e) {
            loginMessage.setText("Cannot open main page for your role!");
        }
    }
}
