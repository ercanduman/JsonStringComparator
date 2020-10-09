package ercanduman.jsondifftask.api;

import ercanduman.jsondifftask.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestControllerTest {
    @Autowired
    RestController controller;

    @Test
    void test_insertLeft_should_insert_json_data_successfully() {
        String json = "{\n  \"content\": \"User name 6 - updated\"\n}";
        String id = "2";
        controller.insertLeft(id, json);
    }

    @Test
    void test_insertLeft_should_throw_exception_if_json_data_Invalid() {
        String json = "\n  content -- \"User name 6 - updated\"\n}";
        String id = "2";
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.insertLeft(id, json));
    }

    @Test
    void test_insertRight_should_insert_json_data_successfully_for_valid_JSON() {
        String json = "{\n  \"content\": \"User name 6 - updated\"\n}";
        String id = "2";
        controller.insertRight(id, json);
    }

    @Test
    void test_insertRight_should_throw_exception_if_json_data_Invalid() {
        String json = "\n  content -- \"User name 6 - updated\"\n}";
        String id = "2";
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.insertRight(id, json));
    }

    @Test
    void test_RESULT_method_should_return_equal_result_if_both_objects_are_equal() {
        String json = "{\n  \"content\": \"User name 6 - updated\"\n}";
        String id = "1";

        controller.insertLeft(id, json);
        controller.insertRight(id, json);

        String expected = Constants.EXC_RESULT_EQUAL;
        String actual = controller.result(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_RESULT_method_should_return_different_SIZE_if_objects_have_different_sizes() {
        String jsonLeft = "{\n  \"content\": \"User name 5 - updated\"\n}";
        String jsonRight = "{\n  \"content\": \"User name 789 - updated\"\n}";
        String id = "1";

        controller.insertLeft(id, jsonLeft);
        controller.insertRight(id, jsonRight);

        String expected = Constants.EXC_RESULT_DIFF_SIZE;
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

        String expected = String.format(Constants.EXC_RESULT_DIFF_OFFSET, 1, "[{\"offset\":22,\"left char\":5,\"right char\":6}]");
        String actual = controller.result(id);

        Assertions.assertEquals(expected, actual);
    }
}