package com.example.product.infrastructure.utils;

import java.util.LinkedHashMap;

public class MapUtils {

    public static Object getData(Object obj, String key) {
        try {
            return ((LinkedHashMap<String, String>) obj).get(key);
        } catch (Exception e) {
            return "";
        }
    }
}
