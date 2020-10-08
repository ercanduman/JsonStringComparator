package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonComparatorTest {

    @Test
    void test_if_both_object_are_NULL_return_that() {
        String result = JsonComparator.compare(null, null);
        assertEquals(Constants.RESULT_OBJECTS_NULL, result);
    }

    @Test
    void test_if_one_object_are_NULL_return_that() {
        String result = JsonComparator.compare(null, new JsonObject("1", "", Side.RIGHT));
        assertEquals(Constants.RESULT_NULL_COMPARISON, result);
    }

    @Test
    void test_if_objects_contents_are_EQUAL_return_that() {
        JsonObject object1 = new JsonObject("1", "", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "", Side.RIGHT);
        String result = JsonComparator.compare(object1, object2);
        assertEquals(Constants.EXC_RESULT_EQUAL, result);
    }

    @Test
    void test_if_objects_contents_are_EQUAL_return_that_2() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 5 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 5 - updated\"\n}", Side.RIGHT);
        String result = JsonComparator.compare(object1, object2);
        assertEquals(Constants.EXC_RESULT_EQUAL, result);
    }

    @Test
    void test_if_objects_have_different_size_return_that() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 5444 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 4 - updated\"\n}", Side.RIGHT);
        String result = JsonComparator.compare(object1, object2);
        assertEquals(Constants.EXC_RESULT_DIFF_SIZE, result);
    }

    @Test
    void test_if_objects_contents_are_different_but_same_size_return_that() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 5 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 4 - updated\"\n}", Side.RIGHT);
        String result = JsonComparator.compare(object1, object2);
        String expected = String.format(Constants.EXC_RESULT_DIFF_OFFSET, 1, "[{\"offset\":26,\"left char\":5,\"right char\":4}]");
        assertEquals(expected, result);
    }

    @Test
    void test_if_objects_contents_are_different_but_same_size_return_that_2() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 8 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
        String result = JsonComparator.compare(object1, object2);
        String expected = String.format(Constants.EXC_RESULT_DIFF_OFFSET, 1, "[{\"offset\":26,\"left char\":8,\"right char\":6}]");
        assertEquals(expected, result);
    }
}