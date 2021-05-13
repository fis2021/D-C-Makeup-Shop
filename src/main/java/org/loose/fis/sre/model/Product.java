package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Product {
    @Id
    private String id;
    private String description;
    private float price;
    //TODO: add image to product
    //private byte[] imageBytes;

    public Product(Product product) {
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Product(String id, String description, float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Float.compare(product.getPrice(), getPrice()) == 0 && getDescription().equals(product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getPrice());
    }
}
