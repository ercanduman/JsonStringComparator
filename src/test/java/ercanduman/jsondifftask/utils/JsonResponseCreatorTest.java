package ercanduman.jsondifftask.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonResponseCreatorTest {
    @Test
    void test_generate_json_if_differences_NULL() {
        boolean isError = false;
        String message = "Inserted successfully.";

        String actual = JsonResponseCreator.response(isError, message, null);

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
        String difference = "[{\"offset\":22,\"left char\":5,\"right char\":6}]";
        String actual = JsonResponseCreator.response(isError, message, difference);

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"Differences\": [\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"left char\": 5,\n" +
                "      \"right char\": 6\n" +
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
                "      \"left char\": 5,\n" +
                "      \"right char\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"left char\": 5,\n" +
                "      \"right char\": 6\n" +
                "    }\n" +
                "  ]";
        String actual = JsonResponseCreator.response(isError, message, difference);

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"Differences\": [\n" +
                "    {\n" +
                "      \"offset\": 22,\n" +
                "      \"left char\": 5,\n" +
                "      \"right char\": 6\n" +
                "    },\n" +
                "{\n" +
                "      \"offset\": 22,\n" +
                "      \"left char\": 5,\n" +
                "      \"right char\": 6\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    @Test
    void test_generate_json_if_differences_provided_3_instances() {
        boolean isError = false;
        String message = "Execution successful.";
        String difference = "[{\"offset\":26,\"left char\":8,\"right char\":6}, {\"offset\":27,\"left char\":2,\"right char\":5}, {\"offset\":28,\"left char\":3,\"right char\":6}]";
        String actual = JsonResponseCreator.response(isError, message, difference);

        String expected = "{\n" +
                "  \"error\": false,\n" +
                "  \"message\": \"Execution successful.\",\n" +
                "  \"Differences\": [\n" +
                "    {\n" +
                "      \"offset\": 26,\n" +
                "      \"left char\": 8,\n" +
                "      \"right char\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 27,\n" +
                "      \"left char\": 2,\n" +
                "      \"right char\": 5\n" +
                "    },\n" +
                "    {\n" +
                "      \"offset\": 28,\n" +
                "      \"left char\": 3,\n" +
                "      \"right char\": 6\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Assertions.assertEquals(removeAllWhiteSpaces(expected), removeAllWhiteSpaces(actual));
    }

    private static String removeAllWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}