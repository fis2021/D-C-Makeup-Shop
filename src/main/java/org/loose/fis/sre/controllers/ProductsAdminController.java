package org.loose.fis.sre.controllers;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.util.*;

import javafx.scene.text.Text;
import javafx.util.converter.FloatStringConverter;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.ProductService;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.model.ProductEditable;
import org.loose.fis.sre.services.UserService;
import javafx.collections.ListChangeListener;

public class ProductsAdminController implements Initializable {


    @FXML
    private TableView<ProductEditable> table_info;

    public static TableView<ProductEditable> table_info_2;

    @FXML
    private TableColumn<ProductEditable, String> col_id;

    @FXML
    private TableColumn<ProductEditable, String> col_description;

    @FXML
    private TableColumn<ProductEditable, Float> col_price;

    @FXML
    private TableColumn<ProductEditable, Button> col_update;

    @FXML
    private TableColumn<ProductEditable, Button> col_delete;

    public static ObservableList<ProductEditable> data_table;

    @Override
    public void  initialize(URL location, ResourceBundle resources) {
        table_info_2 = table_info;
        initTable();
        loadData();
    }

    private void initTable() {
        intiCols();
    }

    private void intiCols() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_update.setCellValueFactory(new PropertyValueFactory<>("update"));
        //cocol_update.

        col_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        editableCols();
    }

    private void editableCols() {
        col_id.setCellFactory(TextFieldTableCell.forTableColumn());
        col_id.setEditable(false);

         col_id.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });


        col_description.setCellFactory(TextFieldTableCell.forTableColumn());
        col_description.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescription(e.getNewValue());
        });

        col_price.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        col_price.setOnEditCommit(e-> {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
        });

        table_info.setEditable(true);

        data_table = FXCollections.observableArrayList();

        /*data_table.addListener(new ListChangeListener<ProductEditable>(){
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends ProductEditable> pChange) {
                while (pChange.next()) {
                    table_info_2 = table_info;
                }
            }
        });*/

    }

     private void loadData() {
         table_info.getItems().clear();

         List<Product> products = ProductService.getProductList();

         System.out.println("aici afisez lista");
         System.out.println(products);

         for (Product product : products){
             data_table.add(new ProductEditable(product, new Button("update"), new Button("delete")));
         }
         table_info.setItems(data_table);
     }



}
