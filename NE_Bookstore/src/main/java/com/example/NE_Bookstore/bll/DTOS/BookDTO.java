package com.example.NE_Bookstore.bll.DTOS;

public class BookDTO {

    private long id;
    private String title;
    private double price;
    private int stock_count;

    private boolean limited;

    public BookDTO() {
        // Default constructor
    }

    public BookDTO(String title, double price, int stock_count, boolean limited) {
        this.title = title;
        this.price = price;
        this.stock_count = stock_count;
        this.limited = limited;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock_count() {
        return stock_count;
    }

    public void setStock_count(int stock_count) {
        this.stock_count = stock_count;
    }

    public boolean isLimited() {
        return limited;
    }

    public void setLimited(boolean limited) {
        this.limited = limited;
    }
}
