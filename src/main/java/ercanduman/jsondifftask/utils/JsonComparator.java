package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Compares two json string
 */
public class JsonComparator {

    /**
     * Compares two JSON strings and returns results based on content of objects.
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
        if (firstObject == null && secondObject == null)
            return JsonResponseCreator.response(false, Constants.RESULT_OBJECTS_NULL, null);

        if (firstObject == null || secondObject == null)
            return JsonResponseCreator.response(false, Constants.RESULT_NULL_COMPARISON, null);

        char[] firstChars = firstObject.getContent().toCharArray();
        char[] secondChars = secondObject.getContent().toCharArray();

        if (Arrays.equals(firstChars, secondChars))
            return JsonResponseCreator.response(false, Constants.EXC_RESULT_EQUAL, null);
        else if (firstChars.length != secondChars.length)
            return JsonResponseCreator.response(false, Constants.EXC_RESULT_DIFF_SIZE, null);
        else {
            List<Difference> differences = new LinkedList<>();
            for (int i = 0; i < firstChars.length; i++) {
                if (firstChars[i] != secondChars[i]) {
                    differences.add(new Difference(i, firstChars[i], secondChars[i]));
                }
            }
            String message = String.format(Constants.EXC_RESULT_DIFF_OFFSET, differences.size());
            return JsonResponseCreator.response(false, message, differences.toString());
        }
    }

    /**
     * Data class to store different characters
     * <p>
     * leftChar will contain non-matched character from left side {@link JsonObject}
     * rightChar will contain non-matched character from right side {@link JsonObject}
     * <p>
     * This object will provide JSON result text with non-matched characters'
     * offset and left and right characters itself.
     */
    static class Difference {
        private final int offset;
        private final Character leftChar;
        private final Character rightChar;

        public Difference(int offset, Character leftChar, Character rightChar) {
            this.offset = offset;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"offset\":" + offset +
                    ",\"left char\":" + leftChar +
                    ",\"right char\":" + rightChar +
                    '}';
        }
    }
}
