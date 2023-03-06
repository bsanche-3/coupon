package com.meli.api.prueba.models;

public class ReqCouponBuilder {

    private final String[] item_ids;
    private final Double amount;

    public ReqCouponBuilder() {
        this.item_ids = new String[]{"MPE624617921","MPE627571744","MPE627519585","MPE627250880","MPE627781130","MPE627858268"};
        this.amount = 500.0;
    }
    public ReqCoupon build() {
        ReqCoupon reqCoupon = new ReqCoupon();

        reqCoupon.setItem_ids(this.item_ids);
        reqCoupon.setAmount(this.amount);

        return reqCoupon;
    }

}
