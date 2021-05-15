package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Order {
    @Id
    private String id;
    private String userId;

    private String orderedProducts;
    private float totalPrice;

    public Order() {

    }

    public Order(String id, String userId, String orderedProducts, float totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(String orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Float.compare(order.getTotalPrice(), getTotalPrice()) == 0 && Objects.equals(getId(), order.getId()) && Objects.equals(getUserId(), order.getUserId()) && Objects.equals(getOrderedProducts(), order.getOrderedProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getOrderedProducts(), getTotalPrice());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderedProducts='" + orderedProducts + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
