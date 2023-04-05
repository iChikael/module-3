package com.codegym.demo.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String NAME_REGEX = "^.*$";
    public static final String QUANTITY_REGEX = "^[1-9][0-9]{1,6}$";
    public static final String PRICE_REGEX = "^[1-9][0-9]{1,8}$";
    public static final String COLOR_REGEX = "^.*$";
    private static final String DESCRIPTION_REGEX = "^.*$";

    public static boolean isNameValid(String name){
        return Pattern.matches(NAME_REGEX, name);
    }

    public static boolean isQuantityValid(String quantity){
        return Pattern.matches(QUANTITY_REGEX, quantity);
    }
    public static boolean isPriceValid(String price){
        return Pattern.matches(PRICE_REGEX, price);
    }
    public static boolean isColorValid(String color){
        return Pattern.matches(COLOR_REGEX, color);
    }

    public static boolean isDersciptionValid(String description) {
        return Pattern.matches(DESCRIPTION_REGEX, description);
    }
}
