package ercanduman.jsondifftask.service;

import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonObjectServiceTest {
    @Autowired
    JsonObjectService service;

    @Test
    void test_insertLeft_method_for_success() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.LEFT);
        service.insertLeft(object);
    }

    @Test
    void test_insertLeft_method_should_throw_exception_if_side_is_RIGHT() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.insertLeft(object));
    }

    @Test
    void test_insertLeft_method_should_throw_exception_if_object_is_NULL() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.insertLeft(null));
    }

    @Test
    void test_insertRight_method_for_success() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
        service.insertRight(object);
    }

    @Test
    void test_insertRight_method_should_throw_exception_if_side_is_LEFT() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.LEFT);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.insertRight(object));
    }

    @Test
    void test_insertRight_method_should_throw_exception_if_object_is_NULL() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.insertRight(null));
    }

/*    @Test
    void test_getLeftObject_method_should_return_null_when_db_is_empty() {
        JsonObject object = service.getLeftObject("1");
        Assertions.assertNull(object);
    }

    @Test()
    void test_getRightObject_method_should_return_null_when_db_is_empty() {
        JsonObject object = service.getRightObject("1");
        Assertions.assertNull(object);
    }*/

    @Test
    void test_getLeftObject_method_should_return_object_which_is_inserted() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.LEFT);
        service.insertLeft(object);

        JsonObject actual = service.getLeftObject("1");
        Assertions.assertEquals(object, actual);
    }

    @Test
    void test_getRightObject_method_should_return_object_which_is_inserted() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
        service.insertRight(object);

        JsonObject actual = service.getRightObject("1");
        Assertions.assertEquals(object, actual);
    }
}