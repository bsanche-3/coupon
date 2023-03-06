package com.meli.api.prueba.utils;

import java.util.Map;

public class Utilidades {

    public static String getKey(Map<String, Double> map, Double value)
    {
        for (Map.Entry<String, Double> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
