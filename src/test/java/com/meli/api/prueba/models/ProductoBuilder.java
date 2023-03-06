package com.meli.api.prueba.models;

public class ProductoBuilder {

    private String item_Id;
    private String title;
    private Double price;
    private String country;

    public ProductoBuilder() {
        this.item_Id = "MPE627968200";
        this.title ="Traspaso Restaurante";
        this.price = 105000.0;
        this.country = "MPE";
    }
    public Producto build() {
        Producto producto = new Producto();

        producto.setItemId(this.item_Id);
        producto.setTitle(this.title);
        producto.setPrice(this.price);
        producto.setCountry(this.country);
        return producto;
    }

}
