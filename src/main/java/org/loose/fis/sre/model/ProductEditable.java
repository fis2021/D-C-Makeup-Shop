package org.loose.fis.sre.model;

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
                ProductEditable productEditable = ProductsAdminController.table_info_2.getSelectionModel().getSelectedItem();
                try {
                        ProductService.updateProduct(new Product(
                                productEditable.getId(),
                                productEditable.getDescription(),
                                productEditable.getPrice()
                        ));
                    } catch (Exception ex) {
                    ProductsAdminController.updateProductMessage.setText(ex.getMessage());
                }
        });

        delete.setOnAction(e -> {
            for (ProductEditable productEditable : ProductsAdminController.data_table) {
                if (productEditable.getUpdate() == delete) {
                    ProductService.deleteProduct(productEditable.getId());

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

}
