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
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.services.UserService;
import org.loose.fis.sre.services.OrderService;
import javafx.collections.ListChangeListener;

public class OrderAdminController implements Initializable {


    @FXML
    private TableView<Order> table_info;

    public static TableView<Order> table_info_2;

    @FXML
    private TableColumn<Order, String> order;

    @FXML
    private TableColumn<Order, String> username;

    @FXML
    private TableColumn<Order, String> col_description;

    @FXML
    private TableColumn<Order, Float> col_price;


    public static ObservableList<Order> data_table;

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
        order.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("userId"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("orderedProducts"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        editableCols();
    }

    private void editableCols() {

        order.setCellFactory(TextFieldTableCell.forTableColumn());
        username.setCellFactory(TextFieldTableCell.forTableColumn());
        col_description.setCellFactory(TextFieldTableCell.forTableColumn());

        col_price.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

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

        List<Order> orders = OrderService.getOrderList();


        for (int i = 0; i < orders.size(); ++i) {
            Order order = orders.get(i);
            data_table.add(order);
        }
        table_info.setItems(data_table);
    }



}
