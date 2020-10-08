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

        String first = firstObject.getContent();
        String second = secondObject.getContent();
        char[] firstChars = first.toCharArray();
        char[] secondChars = second.toCharArray();

        String result;
        if (first.equals(second)) {
            result = "Objects are equal - \n" + first + "\n" + second;
        } else if (first.length() != second.length()) {
            result = "Objects have different sizes";
        } else {
            int length = first.length();

            char[] differences = new char[length];

            int offset = -1;
            for (int i = 0; i < length; i++) {
                if (firstChars[i] != secondChars[i]) {
                    differences[i] = firstChars[i];

                    if (offset < 0) offset = i;
                }
            }
            result = "Objects have same size b ut has differences starts at offset: " + offset
                    + " \nDifferences are:" + Arrays.toString(differences)
                    + " \nDiff length: " + differences.length;
        }
        return result;
    }
}
