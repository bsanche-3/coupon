package com.meli.api.prueba.models;

public class SiteBuilder {

    private String default_currency_id;
    private String id;
    private String name;

    public SiteBuilder() {
        this.default_currency_id = "DOP";
        this.id ="MRD";
        this.name = "Dominicana";
    }

    public Site build() {
        Site site = new Site();

        site.setDefaultCurrencyId(this.default_currency_id);
        site.setId(this.id);
        site.setName(this.name);
        return site;
    }

}
