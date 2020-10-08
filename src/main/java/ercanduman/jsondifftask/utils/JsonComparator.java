package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

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
        if (firstObject == null && secondObject == null) return Constants.RESULT_OBJECTS_NULL;

        if (firstObject == null || secondObject == null) return Constants.RESULT_NULL_COMPARISON;

        char[] firstChars = removeAllWhiteSpaces(firstObject.getContent()).toCharArray();
        char[] secondChars = removeAllWhiteSpaces(secondObject.getContent()).toCharArray();

        String result;
        if (Arrays.equals(firstChars, secondChars)) result = Constants.EXC_RESULT_EQUAL;
        else if (firstChars.length != secondChars.length) result = Constants.EXC_RESULT_DIFF_SIZE;
        else {

            Map<Integer, Character> differences = new LinkedHashMap<>(); // LinkedHashMap maintains the insertion order.
            for (int i = 0; i < firstChars.length; i++) {
                if (firstChars[i] != secondChars[i]) {
                    differences.put(i, firstChars[i]);
                }
            }
            result = String.format(Constants.EXC_RESULT_DIFF_OFFSET, differences.size(), differences.toString());
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
