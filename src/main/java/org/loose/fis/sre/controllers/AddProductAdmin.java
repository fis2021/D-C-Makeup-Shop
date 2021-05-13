package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.ProductService;
import org.loose.fis.sre.services.UserService;

public class AddProductAdmin {
    @FXML
    private Text addProductMessage;


    @FXML
    public static Text updateProductMessage;

    @FXML
    private Text description;

    @FXML
    private TextField descriptionField;

    @FXML
    private Text price;
    @FXML
    private TextField priceField;

    @FXML
    public void handleAddProductAction() {
        try {
            String description = descriptionField.getText();
            if (description.length() < 3) {
                addProductMessage.setText("Description too short!!");
            } else {
                float price = Float.parseFloat(priceField.getText());
                ProductService.addProduct(descriptionField.getText(), price);
                addProductMessage.setText("Product added successfully!");
            }
        } catch (Exception e) {
            addProductMessage.setText(e.getMessage());
        }
    }
}
