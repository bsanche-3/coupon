package com.meli.api.prueba.models;

public class CategorieBuilder {

    private String id;
    private String name;

    public CategorieBuilder() {
        this.id = "MPE1747";
        this.name ="Accesorios para Vehículos";
    }
    public Categorie build() {
        Categorie categorie = new Categorie();

        categorie.setId(this.id);
        categorie.setName(this.name);

        return categorie;
    }

}
