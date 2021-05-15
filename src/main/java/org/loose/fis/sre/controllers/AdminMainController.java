package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMainController {
    @FXML
    public void handleAddProductAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/addProductAdmin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("D&C Make-up Shop Admin Add Products View");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleUpdateProductsAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/productsViewAdmin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("D&C Make-up Shop Admin Order View");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void handleViewOrdersAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/TheOrderViewAdmin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("D&C Make-up Shop Admin Order View");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
