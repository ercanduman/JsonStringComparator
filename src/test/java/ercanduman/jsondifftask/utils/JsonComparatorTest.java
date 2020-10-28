package ercanduman.jsondifftask.utils;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JsonComparatorTest {

    @Autowired
    private JsonComparator comparator;
    @Autowired
    private JsonResponseCreator responseCreator;

    @Test
    void test_if_both_object_are_NULL_return_that() {
        String result = comparator.compare(null, null);
        
        responseCreator.setError(false);
        responseCreator.setMessage(Constants.RESULT_OBJECTS_NULL);
        responseCreator.setDifferences(null);
        String expected = responseCreator.toString();
        assertEquals(expected, result);
    }

    @Test
    void test_if_one_object_are_NULL_return_that() {
        String result = comparator.compare(null, new JsonObject("1", "", Side.RIGHT));

        responseCreator.setError(false);
        responseCreator.setMessage(Constants.RESULT_NULL_COMPARISON);
        responseCreator.setDifferences(null);
        String expected = responseCreator.toString();
        assertEquals(expected, result);
    }

    @Test
    void test_if_objects_contents_are_EQUAL_return_that() {
        JsonObject object1 = new JsonObject("1", "", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "", Side.RIGHT);
        String result = comparator.compare(object1, object2);
        
        responseCreator.setError(false);
        responseCreator.setMessage(Constants.EXC_RESULT_EQUAL);
        responseCreator.setDifferences(null);
        String expected = responseCreator.toString();
        assertEquals(expected, result);
    }

    @Test
    void test_if_objects_contents_are_EQUAL_return_that_2() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 5 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 5 - updated\"\n}", Side.RIGHT);
        String result = comparator.compare(object1, object2);

        responseCreator.setError(false);
        responseCreator.setMessage(Constants.EXC_RESULT_EQUAL);
        responseCreator.setDifferences(null);
        String expected = responseCreator.toString();
        assertEquals(expected, result);
    }

    @Test
    void test_if_objects_have_different_size_return_that() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 5444 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 4 - updated\"\n}", Side.RIGHT);
        String result = comparator.compare(object1, object2);

        responseCreator.setError(false);
        responseCreator.setMessage(Constants.EXC_RESULT_DIFF_SIZE);
        responseCreator.setDifferences(null);
        String expected = responseCreator.toString();
        assertEquals(expected, result);
    }

    @Test
    void test_if_objects_contents_are_different_but_same_size_return_that() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 5 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 4 - updated\"\n}", Side.RIGHT);
        String result = comparator.compare(object1, object2);

        String message = String.format(Constants.EXC_RESULT_DIFF_OFFSET, 1);

        responseCreator.setError(false);
        responseCreator.setMessage(message);

        JsonComparator.Difference difference = new JsonComparator.Difference(26, '5', '4');
        responseCreator.setDifferences(List.of(difference));
        String expected = responseCreator.toString();

        assertEquals(expected, result);
    }

    @Test
    void test_if_objects_contents_are_different_but_same_size_return_that_2() {
        JsonObject object1 = new JsonObject("1", "{\n  \"content\": \"User name 8 - updated\"\n}", Side.LEFT);
        JsonObject object2 = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
        String result = comparator.compare(object1, object2);

        String message = String.format(Constants.EXC_RESULT_DIFF_OFFSET, 1);
        responseCreator.setError(false);
        responseCreator.setMessage(message);

        JsonComparator.Difference difference = new JsonComparator.Difference(26, '8', '6');
        responseCreator.setDifferences(List.of(difference));
        String expected = responseCreator.toString();

        assertEquals(expected, result);
    }
}