package com.gia.billing.model;


public class Cart {
    private Products products;
    private String code;
    private String name;
    private int product_Image;
    private int noOfQuantity;
    private int quantity;
    private String quantityType;
    private float price;
    private float total_price;

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProduct_Image() {
        return product_Image;
    }

    public void setProduct_Image(int product_Image) {
        this.product_Image = product_Image;
    }

    public int getNoOfQuantity() {
        return noOfQuantity;
    }

    public void setNoOfQuantity(int noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }
}
