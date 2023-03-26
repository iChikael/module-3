package com.codegym.displaycustomer;

import java.util.Date;

public class Customer {
    private long id;
    private String name;
    private Date createAt;
    private String address;
    private String image;

    public Customer(long id, String name, Date createAt, String address, String image) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.address = address;
        this.image = image;
    }

    public Customer() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

