package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.data.entity.JsonObject;

import java.util.Arrays;

/**
 * Compares two json string
 */
public class JsonComparator {

    /**
     * Compares two JSON strings and returns results based on equality of data
     * <p>
     * if two JSON data are equal return that
     * if Json objects are different but have same length, returns that
     * if Objects' sizes are equal then return different offset and length of difference
     *
     * @param firstObject  first {@link JsonObject}
     * @param secondObject second {@link JsonObject}
     * @return result text
     */
    public static String compare(JsonObject firstObject, JsonObject secondObject) {
        if (firstObject == null && secondObject == null) return "Both objects are NULL";

        if (firstObject == null || secondObject == null) return "NULL comparison not allowed";

        char[] firstChars = removeAllWhiteSpaces(firstObject.getContent()).toCharArray();
        char[] secondChars = removeAllWhiteSpaces(secondObject.getContent()).toCharArray();

        String result;
        if (Arrays.equals(firstChars, secondChars)) {
            result = "Objects are equal - \n" + firstObject.getContent() + "\n" + secondObject.getContent();
        } else if (firstChars.length != secondChars.length) {
            result = "Objects have different sizes";
        } else {

            // This array created in order to display differences text
            char[] differences = new char[firstChars.length];
            int offset = -1;
            for (int i = 0; i < firstChars.length; i++) {
                if (firstChars[i] != secondChars[i]) {
                    differences[i] = firstChars[i];
                    if (offset < 0) offset = i;
                }
            }
            result = "Objects have same size b ut has differences starts at offset: " + offset
                    + " \nDifference length :" + differences.length;
        }
        return result;
    }

    /**
     * Removes all white spaces from input text
     *
     * @param input text to remove spaces from.
     * @return returns pure string with text
     */
    private static String removeAllWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}
