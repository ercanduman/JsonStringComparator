package ercanduman.jsondifftask.api;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.utils.JsonResponseCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestControllerTest {
    @Autowired
    RestController controller;
    @Autowired
    private JsonResponseCreator responseCreator;

    @Test
    void test_insertLeft_should_insert_json_data_successfully() {
        String json = "{\n  \"content\": \"User name 6 - updated\"\n}";
        String id = "2";
        controller.insertLeft(id, json);
    }

    @Test
    void test_insertLeft_should_return_error_message_if_json_data_Invalid() {
        String json = "\n  content -- \"User name 6 - updated\"\n}";
        String id = "2";
        String actual = controller.insertLeft(id, json);
        String expected = responseCreator.response(true, INVALID_JSON_ERROR, null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_insertRight_should_insert_json_data_successfully_for_valid_JSON() {
        String json = "{\n  \"content\": \"User name 6 - updated\"\n}";
        String id = "2";
        controller.insertRight(id, json);
    }

    @Test
    void test_insertRight_should_return_error_message_if_json_data_Invalid() {
        String json = "\n  content -- \"User name 6 - updated\"\n}";
        String id = "2";
        String actual = controller.insertRight(id, json);
        String expected = responseCreator.response(true, INVALID_JSON_ERROR, null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_RESULT_method_should_return_equal_result_if_both_objects_are_equal() {
        String json = "{\n  \"content\": \"User name 6 - updated\"\n}";
        String id = "1";

        controller.insertLeft(id, json);
        controller.insertRight(id, json);

        String actual = controller.result(id);
        String expected = responseCreator.response(false, Constants.EXC_RESULT_EQUAL, null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_RESULT_method_should_return_different_SIZE_if_objects_have_different_sizes() {
        String jsonLeft = "{\n  \"content\": \"User name 5 - updated\"\n}";
        String jsonRight = "{\n  \"content\": \"User name 789 - updated\"\n}";
        String id = "1";

        controller.insertLeft(id, jsonLeft);
        controller.insertRight(id, jsonRight);

        String expected = responseCreator.response(false, Constants.EXC_RESULT_DIFF_SIZE, null);
        String actual = controller.result(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_RESULT_method_should_return_differences_if_objects_have_different_content() {
        String jsonLeft = "{\n  \"content\": \"User name 5 - updated\"\n}";
        String jsonRight = "{\n  \"content\": \"User name 6 - updated\"\n}";
        String id = "1";

        controller.insertLeft(id, jsonLeft);
        controller.insertRight(id, jsonRight);

        String message = String.format(Constants.EXC_RESULT_DIFF_OFFSET, 1);
        String differences = "[{\"offset\":22,\"left char\":5,\"right char\":6}]";
        String expected = responseCreator.response(false, message, differences);

        String actual = controller.result(id);
        Assertions.assertEquals(expected, actual);
    }

    private static final String INVALID_JSON_ERROR = "Invalid JSON found! Error: Unrecognized token 'content': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')\n" +
            " at [Source: (String)\"\n" +
            "  content -- \"User name 6 - updated\"\n" +
            "}\"; line: 2, column: 10]";
}