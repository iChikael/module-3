package com.codegym.customermanager.model;

public class CustomerType {
    private long id;
    private String name;

    public CustomerType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerType() {
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
}
