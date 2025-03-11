package com.example.ecommerce.backend;

public class Order {
    private int orderID;
    private String orderDate;
    private String orderStatus;
    private float orderTotal;
    private String orderDetails;

    public Order(int orderID, String orderDate, String orderStatus, float orderTotal, String orderDetails) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.orderDetails = orderDetails;
    }
    public int getOrderID() {
        return orderID;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public float getOrderTotal() {
        return orderTotal;
    }
    public String getOrderDetails() {
        return orderDetails;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
}
