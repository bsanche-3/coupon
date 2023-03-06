package com.meli.api.prueba.models;

public class Favoritos {
    private String itemId;
    private Double conteo;

    public Favoritos(String itemId, Double conteo) {
        super();
        this.itemId = itemId;
        this.conteo = conteo;
    }

    public Favoritos() {
        super();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getConteo() {
        return conteo;
    }

    public void setConteo(Double conteo) {
        this.conteo = conteo;
    }
}
