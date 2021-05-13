package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UserNotFoundException;
import org.loose.fis.sre.model.User;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LoginController {
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    public void openMainPageForRole(String resourceFilename, String titleStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(resourceFilename));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(titleStage);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    public void handleLoginAction() {
        try {
            final User loggedUser = UserService.loginUser(usernameField.getText(), passwordField.getText());
            //loginMessage.setText("Login successful!");
            if (loggedUser.getRole().equals("Admin")) {
                openMainPageForRole("/adminmain.fxml", "D&C Make-up Shop Admin Main Page");
            } else {
                openMainPageForRole("/adminmain.fxml", "D&C Make-up Shop Client Main Page");
            }

        } catch (UserNotFoundException e) {
            long noOfUsers = UserService.getNoOfUsers();
            loginMessage.setText("Number of users for debugging: " + noOfUsers);
        } catch (IOException e) {
            loginMessage.setText("Cannot open main page for your role!");
        }
    }
}
