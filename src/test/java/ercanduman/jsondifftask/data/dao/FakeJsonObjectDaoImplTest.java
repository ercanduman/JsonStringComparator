package ercanduman.jsondifftask.data.dao;

import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FakeJsonObjectDaoImplTest {
    FakeJsonObjectDaoImpl fakeDao = new FakeJsonObjectDaoImpl();

    @Test
    void test_insert_left_success() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.LEFT);
        fakeDao.insertLeft(object);
    }

    @Test
    void test_if_object_side_isRIGHT_insert_left_should_throw_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
            fakeDao.insertLeft(object);
        });
    }

    @Test
    void test_if_object_is_NULL_insert_left_should_throw_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> fakeDao.insertLeft(null));
    }

    @Test
    void test_insert_right_success() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
        fakeDao.insertRight(object);
    }

    @Test
    void test_if_object_side_isLEFT_insert_right_should_throw_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.LEFT);
            fakeDao.insertRight(object);
        });
    }

    @Test
    void test_if_object_is_NULL_insert_right_should_throw_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> fakeDao.insertRight(null));
    }

    @Test
    void test_since_left_object_db_is_empty_getLeftObject_method_should_return_null() {
        JsonObject actual = fakeDao.getLeftObject("1");
        Assertions.assertNull(actual);
    }

    @Test
    void test_after_inserting_1_object_getLeftObject_method_should_return_it() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.LEFT);
        fakeDao.insertLeft(object);
        JsonObject actual = fakeDao.getLeftObject("1");
        Assertions.assertEquals(object, actual);
    }

    @Test
    void test_since_right_object_db_is_empty_getRightObject_method_should_return_null() {
        JsonObject actual = fakeDao.getRightObject("1");
        Assertions.assertNull(actual);
    }

    @Test
    void test_after_inserting_1_object_getRightObject_method_should_return_it() {
        JsonObject object = new JsonObject("1", "{\n  \"content\": \"User name 6 - updated\"\n}", Side.RIGHT);
        fakeDao.insertRight(object);
        JsonObject actual = fakeDao.getRightObject("1");
        Assertions.assertEquals(object, actual);
    }
}