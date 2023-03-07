package com.meli.api.prueba.utils;

import java.util.*;

public class SumInversa {
    static void sumInversaRecursive(ArrayList<Double> prices, double coupon, ArrayList<Double> partial, Map<Double,ArrayList<Double>> arrItemsSelected) {
        double s = 0.0;
        for (double x: partial) {
            s = s + x;
        }
        if (s > 0.0 && s <= coupon){
            arrItemsSelected.put(s,partial);
        }
        if (s > coupon){
            return;
        }

        for(int i=0;i<prices.size();i++) {
            ArrayList<Double> remaining = new ArrayList<>();
            double n = prices.get(i);
            for (int j=i+1; j<prices.size();j++) {
                remaining.add(prices.get(j));
            }
            ArrayList<Double> arrPartialRec = new ArrayList<>(partial);
            arrPartialRec.add(n);
            sumInversaRecursive(remaining,coupon,arrPartialRec,arrItemsSelected);
        }
    }
    public static List<Map.Entry<Double,ArrayList<Double>>> sumInversaUp(List<Double> prices, double target) {

        Map<Double,ArrayList<Double>> mapItemsSelected = new HashMap<>();
        sumInversaRecursive((ArrayList<Double>) prices,target,new ArrayList<>(),mapItemsSelected);

        List<Map.Entry<Double,ArrayList<Double>>> itemsCouponSorted;

        itemsCouponSorted = new ArrayList<>(mapItemsSelected.entrySet());
        itemsCouponSorted.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        return itemsCouponSorted;
    }
}