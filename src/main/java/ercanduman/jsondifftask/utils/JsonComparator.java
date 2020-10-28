package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Compares two json string
 */
@Component
public class JsonComparator {

    @Autowired
    private JsonResponseCreator responseCreator;

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
    public String compare(JsonObject firstObject, JsonObject secondObject) {
        responseCreator.setError(false);
        responseCreator.setDifferences(null);
        if (firstObject == null && secondObject == null) {
            responseCreator.setMessage(Constants.RESULT_OBJECTS_NULL);
            return responseCreator.toString();
        }

        if (firstObject == null || secondObject == null) {
            responseCreator.setMessage(Constants.RESULT_NULL_COMPARISON);
            return responseCreator.toString();
        }

        char[] firstChars = firstObject.getContent().toCharArray();
        char[] secondChars = secondObject.getContent().toCharArray();

        if (Arrays.equals(firstChars, secondChars)) {
            responseCreator.setMessage(Constants.EXC_RESULT_EQUAL);
            return responseCreator.toString();
        } else if (firstChars.length != secondChars.length) {
            responseCreator.setMessage(Constants.EXC_RESULT_DIFF_SIZE);
            return responseCreator.toString();
        } else {
            List<Difference> differences = new LinkedList<>();
            for (int i = 0; i < firstChars.length; i++) {
                if (firstChars[i] != secondChars[i]) {
                    differences.add(new Difference(i, firstChars[i], secondChars[i]));
                }
            }
            responseCreator.setMessage(String.format(Constants.EXC_RESULT_DIFF_OFFSET, differences.size()));
            responseCreator.setDifferences(differences);
            return responseCreator.toString();
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
    public static class Difference {
        private final int offset;
        private final Character leftChar;
        private final Character rightChar;

        public Difference(int offset, Character leftChar, Character rightChar) {
            this.offset = offset;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }

        public int getOffset() {
            return offset;
        }

        public Character getLeftChar() {
            return leftChar;
        }

        public Character getRightChar() {
            return rightChar;
        }
    }
}
