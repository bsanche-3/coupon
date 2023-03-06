package com.meli.api.prueba.models;

public class ResCouponBuilder {

    private final String[] item_ids;
    private final Double total;

    public ResCouponBuilder() {
        this.item_ids = new String[]{"MPE627519585","MPE627250880","MPE627781130","MPE627858268"};
        this.total = 250.0;
    }
    public ResCoupon build() {
        ResCoupon resCoupon = new ResCoupon();

        resCoupon.setItemIds(this.item_ids);
        resCoupon.setTotal(this.total);

        return resCoupon;
    }

}
