package com.meli.api.prueba.models;

public class Producto {
    private String itemId;
    private String title;
    private Double price;
    private String country;

    public Producto(String itemId, String title, String country, Double price) {
        super();
        this.itemId = itemId;
        this.title = title;
        this.price = price;
        this.country = country;
    }
    public Producto() {
        super();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
