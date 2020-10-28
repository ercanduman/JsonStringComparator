package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class JsonResponseCreatorTest {
    @Autowired
    private JsonResponseCreator responseCreator;

    @Test
    void test_json_response_to_create_json_string() {
        JsonComparator.Difference difference = new JsonComparator.Difference(1, '3', '5');
        responseCreator.setError(false);
        responseCreator.setMessage(Constants.EXC_RESULT_DIFF_OFFSET);
        responseCreator.setDifferences(Collections.singletonList(difference));

        String notCorrectOrder = "{\"error\":false,\"message\":\"Execution successful. Objects have same size but differences. Difference length: %s.\",\"differences\":[{\"offset\":1,\"leftChar\":\"3\",\"rightChar\":\"5\"}]}";
        String result3 = responseCreator.toString();
        System.out.println(result3);
        Assertions.assertEquals(notCorrectOrder, result3);
    }

    @Test
    void test_generate_json_if_differences_NULL() {
        String message = "Inserted successfully.";
        responseCreator.setError(false);
        responseCreator.setMessage(message);
        responseCreator.setDifferences(null);

        String actual = responseCreator.toString();

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Inserted successfully.\"\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    @Test
    void test_generate_json_if_differences_provided() {
        String message = "Execution successful.";
        JsonComparator.Difference difference = new JsonComparator.Difference(22, '5', '6');

        responseCreator.setError(false);
        responseCreator.setMessage(message);
        responseCreator.setDifferences(List.of(difference));

        String actual = responseCreator.toString();

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"differences\":[\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"leftChar\": \"5\",\n" +
                "      \"rightChar\": \"6\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    @Test
    void test_generate_json_if_differences_provided_2_instances() {
        String message = "Execution successful.";
        JsonComparator.Difference difference1 = new JsonComparator.Difference(22, '5', '6');
        JsonComparator.Difference difference2 = new JsonComparator.Difference(23, '5', '6');

        responseCreator.setError(false);
        responseCreator.setMessage(message);
        responseCreator.setDifferences(List.of(difference1, difference2));

        String actual = responseCreator.toString();

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"differences\":[\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"leftChar\": \"5\",\n" +
                "      \"rightChar\": \"6\"\n" +
                "    },\n" +
                "{\n" +
                "      \"offset\": 23,\n" +
                "      \"leftChar\": \"5\",\n" +
                "      \"rightChar\": \"6\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    @Test
    void test_generate_json_if_differences_provided_3_instances() {
        String message = "Execution successful.";

        JsonComparator.Difference difference1 = new JsonComparator.Difference(26, '8', '6');
        JsonComparator.Difference difference2 = new JsonComparator.Difference(27, '2', '5');
        JsonComparator.Difference difference3 = new JsonComparator.Difference(28, '3', '6');

        responseCreator.setError(false);
        responseCreator.setMessage(message);
        responseCreator.setDifferences(List.of(difference1, difference2, difference3));
        String actual = responseCreator.toString();

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"differences\":[\n" +
                "    {\n" +
                "      \"offset\": 26,\n" +
                "      \"leftChar\": \"8\",\n" +
                "      \"rightChar\": \"6\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 27,\n" +
                "      \"leftChar\": \"2\",\n" +
                "      \"rightChar\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 28,\n" +
                "      \"leftChar\": \"3\",\n" +
                "      \"rightChar\": \"6\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    private static String removeAllWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}