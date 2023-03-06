package com.meli.api.prueba.models;

public class Site {
    private String defaultCurrencyId;
    private String id;
    private String name;

    public Site(String defaultCurrencyId, String id, String name) {
        super();
        this.defaultCurrencyId = defaultCurrencyId;
        this.id = id;
        this.name = name;
    }
    public Site() {
        super();
    }

    public String getDefaultCurrencyId() {
        return defaultCurrencyId;
    }

    public void setDefaultCurrencyId(String defaultCurrencyId) {
        this.defaultCurrencyId = defaultCurrencyId;
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
