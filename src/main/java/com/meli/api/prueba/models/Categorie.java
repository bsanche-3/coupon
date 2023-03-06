package com.meli.api.prueba.models;

public class Categorie {
    private String id;
    private String name;

    public Categorie(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public Categorie() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
