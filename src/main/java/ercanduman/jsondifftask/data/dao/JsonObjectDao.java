package ercanduman.jsondifftask.data.dao;

import ercanduman.jsondifftask.data.entity.JsonObject;

import java.util.List;

/**
 * Data Access object. Defines the pattern and all functionalities to be used for different DAO implementations.
 */
public interface JsonObjectDao {
    void insertLeft(JsonObject object);

    void insertRight(JsonObject object);

    void update(JsonObject object);

    List<JsonObject> getObjects(String id);

    JsonObject getLeftObject(String id);

    JsonObject getRightObject(String id);
}
