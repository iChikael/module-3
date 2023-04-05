package com.codegym.demo.model;

public class Product {
    private long id;

    public String nameP;
    private int type;
    private int quantity;
    private int price;
    private String description;


    public Product(long id, String name, int quantity, int price, String description, int type) {
        this.id = id;
        this.nameP = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.description = description;

    }

    public Product() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return nameP;
    }

    public void setName(String name) {
        this.nameP = name;
    }

    public long getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
//        description.equals("aa");
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void add(Product product) {
    }
}
