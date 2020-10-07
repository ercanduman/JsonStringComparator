package ercanduman.jsondifftask.data.dao;

import ercanduman.jsondifftask.data.entity.JsonObject;

import java.util.List;

/**
 * Data Access object pattern and all functionalities
 */
public interface JsonObjectDao {
    void insert(JsonObject object);

    void update(JsonObject object);

    List<JsonObject> getObjects(String id);

    JsonObject getLeftObject(String id);

    JsonObject getRightObject(String id);
}
