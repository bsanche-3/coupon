package com.meli.api.prueba.models;

public class ResCoupon {
    private String[] itemIds;
    private Double total;

    public ResCoupon(String[] itemIds, Double total) {
        super();
        this.itemIds = itemIds;
        this.total = total;
    }

    public ResCoupon() {
        super();
    }

    public String[] getItemIds() {
        return itemIds;
    }

    public void setItemIds(String[] itemIds) {
        this.itemIds = itemIds;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
