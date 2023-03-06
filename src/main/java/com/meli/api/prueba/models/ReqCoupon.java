package com.meli.api.prueba.models;

public class ReqCoupon {
    private String[] item_ids;
    private Double amount;

    public ReqCoupon(String[] item_ids, Double amount) {
        super();
        this.item_ids = item_ids;
        this.amount = amount;
    }

    public ReqCoupon() {
        super();
    }

    public String[] getItem_ids() {
        return item_ids;
    }

    public void setItem_ids(String[] item_ids) {
        this.item_ids = item_ids;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
