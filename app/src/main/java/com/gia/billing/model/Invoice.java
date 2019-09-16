package com.gia.billing.model;

public class Invoice {

    private int itemNo;
    private int product_invoice_no;
    private String name;
    private int quantity;
    private String quantityType;
    private float price;

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public int getProduct_invoice_no() {
        return product_invoice_no;
    }

    public void setProduct_invoice_no(int product_invoice_no) {
        this.product_invoice_no = product_invoice_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
