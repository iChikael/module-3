package com.codegym.Product;

import java.util.Date;

public class Product {
    private long id;
    private String name;
    private Date createAt;
    private String quantity;
    private String price;

    public Product(long id, String name, Date createAt, String quantity, String price) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.quantity = quantity;
        this.price = price;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCreatedDate(Date createdDate) {
    }

    public void setPriceOut(String priceOut) {
    }

    public void setPriceIn(String priceIn) {
    }
}
