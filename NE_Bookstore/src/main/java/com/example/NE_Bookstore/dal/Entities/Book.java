package com.example.NE_Bookstore.dal.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock_count;


    @Column(nullable = false)
    private boolean limited;

    // Constructors
    public Book() {
        // Default constructor
    }

    public Book(String title, double price, int stock_count) {
        this.title = title;
        this.price = price;
        this.stock_count = stock_count;
    }

    // Getters and Setters
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