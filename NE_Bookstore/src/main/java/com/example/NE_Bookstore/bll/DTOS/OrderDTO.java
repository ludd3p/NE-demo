package com.example.NE_Bookstore.bll.DTOS;

import java.util.List;

public class OrderDTO {
    private long id;
    private double totalCost;
    private UserDTO user;
    private List<OrderItemDTO> orderItems;
    private boolean completed;

    public OrderDTO() {
    }

    public OrderDTO(long id, double totalCost, UserDTO user, List<OrderItemDTO> orderItems, boolean completed) {
        this.id = id;
        this.totalCost = totalCost;
        this.user = user;
        this.orderItems = orderItems;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}