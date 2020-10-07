package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.data.entity.JsonObject;

import java.util.Arrays;

public class JsonComparator {

    // if both objects are equal, then return "Objects are equal"
    // If their sizes are different, then return "Different size"
    // If sizes are equal then return different offsets length of difference 

    public static String compare(JsonObject firstObject, JsonObject secondObject) {
        if (firstObject == null && secondObject == null) return "Both objects are NULL";
        
        if (firstObject == null || secondObject == null) return "NULL comparison not allowed";

        byte[] first = firstObject.getContent().getBytes();
        byte[] second = secondObject.getContent().getBytes();

        String result;
        if (Arrays.equals(first, second)) result = "Objects are equal";
        else if (first.length != second.length) result = "Objects have different sizes";
        else {

            result = "";
        }
        return result;
    }
}
