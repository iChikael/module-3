package com.codegym.customermanager.model;

import java.util.Date;

public class Customer {
    private long id;
    private String name;
    private String email;
    private String address;
    private String image;
    private Date createAt;

    private long customerType;

    public long getCustomerType() {
        return customerType;
    }

    public void setCustomerType(long customerType) {
        this.customerType = customerType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Customer(long id, String name, String email,
                    String address, String image, Date createAt, long customerType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.image = image;
        this.createAt = createAt;
        this.customerType = customerType;
    }
}
