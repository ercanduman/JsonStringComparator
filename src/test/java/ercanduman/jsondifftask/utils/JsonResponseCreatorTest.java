package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

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
        boolean isError = false;
        String message = "Inserted successfully.";

        String actual = responseCreator.response(isError, message, null);

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Inserted successfully.\"\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    @Test
    void test_generate_json_if_differences_provided() {
        boolean isError = false;
        String message = "Execution successful.";
        String difference = "[{\"offset\":22,\"leftChar\":5,\"rightChar\":6}]";
        String actual = responseCreator.response(isError, message, difference);

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"differences\":[\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"leftChar\": 5,\n" +
                "      \"rightChar\": 6\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    @Test
    void test_generate_json_if_differences_provided_2_instances() {
        boolean isError = false;
        String message = "Execution successful.";
        String difference = "[\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"leftChar\": 5,\n" +
                "      \"rightChar\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"leftChar\": 5,\n" +
                "      \"rightChar\": 6\n" +
                "    }\n" +
                "  ]";
        String actual = responseCreator.response(isError, message, difference);

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"differences\":[\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"leftChar\": 5,\n" +
                "      \"rightChar\": 6\n" +
                "    },\n" +
                "{\n" +
                "      \"offset\": 22,\n" +
                "      \"leftChar\": 5,\n" +
                "      \"rightChar\": 6\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    @Test
    void test_generate_json_if_differences_provided_3_instances() {
        boolean isError = false;
        String message = "Execution successful.";
        String difference = "[{\"offset\":26,\"leftChar\":8,\"rightChar\":6}, {\"offset\":27,\"leftChar\":2,\"rightChar\":5}, {\"offset\":28,\"leftChar\":3,\"rightChar\":6}]";
        String actual = responseCreator.response(isError, message, difference);

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"differences\":[\n" +
                "    {\n" +
                "      \"offset\": 26,\n" +
                "      \"leftChar\": 8,\n" +
                "      \"rightChar\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 27,\n" +
                "      \"leftChar\": 2,\n" +
                "      \"rightChar\": 5\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 28,\n" +
                "      \"leftChar\": 3,\n" +
                "      \"rightChar\": 6\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    private static String removeAllWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}