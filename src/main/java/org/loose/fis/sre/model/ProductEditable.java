package org.loose.fis.sre.model;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import org.loose.fis.sre.controllers.ProductsAdminController;
import org.loose.fis.sre.services.ProductService;

public class ProductEditable extends Product {
    Button update, delete;

    public ProductEditable(Product product, Button update, Button delete) {
        super(product);
        this.update = update;
        this.delete = delete;

        update.setOnAction(e -> {

            //ProductEditable x = ProductsAdminController.table_info_2.getSelectionModel().getSelectedItem();
            /*ProductService.updateProduct(new Product(
                    x.getId(),
                    x.getDescription(),
                    x.getPrice()));*/
                ObservableList<ProductEditable> productsEditable = ProductsAdminController.table_info_2.getSelectionModel().getSelectedItems();
                try {
                    for (ProductEditable productEditable : productsEditable) {
                        if (productEditable.getUpdate() == update) {
                            ProductService.updateProduct(new Product(
                                    productEditable.getId(),
                                    productEditable.getDescription(),
                                    productEditable.getPrice()));

                            System.out.println("id " + productEditable.getId());
                        }
                    }


                    } catch (Exception ex) {
                    //ProductsAdminController.updateProductMessage.setText(ex.getMessage());
                }
        });

        delete.setOnAction(e -> {
            for (ProductEditable productEditable : ProductsAdminController.data_table) {
                if (productEditable.getUpdate() == delete) {
                    ProductService.deleteProduct(productEditable.getId());
                    //TODO: refresh table after product deletion
                }
            }
        });
    }

    @Override
    public String toString() {
        return "ProductEditable{" +
                super.toString() +
                "update=" + update +
                ", delete=" + delete +
                '}';
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    public float getPrice() {
        return super.getPrice();
    }

    public void setPrice(float price) {
        super.setPrice(price);
    }

    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

}
