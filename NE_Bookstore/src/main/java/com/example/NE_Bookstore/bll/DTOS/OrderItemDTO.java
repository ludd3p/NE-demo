package com.example.NE_Bookstore.bll.DTOS;

public class OrderItemDTO {
    private Long id;

    private Long orderId;
    private BookDTO book;
    private double price;
    private int quantity;
    private double totalPrice;


    public OrderItemDTO() {
    }

    public OrderItemDTO(Long id, BookDTO book, double price, int quantity, double totalPrice) {
        this.id = id;
        this.book = book;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
