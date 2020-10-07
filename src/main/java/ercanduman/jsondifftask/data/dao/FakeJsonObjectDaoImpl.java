package ercanduman.jsondifftask.data.dao;

import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Data access object fake implementation
 * <p>
 * This object overrides all {@link JsonObjectDao} interface pattern methods and handles invalid Side enum inputs
 */
public class FakeJsonObjectDaoImpl implements JsonObjectDao {
    private HashMap<String, JsonObject> leftObjects = new HashMap<>();
    private HashMap<String, JsonObject> rightObjects = new HashMap<>();

    @Override
    public void insert(JsonObject object) {
        if (isRightObject(object)) rightObjects.put(object.getId(), object);
        else if (isLeftObject(object)) leftObjects.put(object.getId(), object);
        else {
            throw new IllegalArgumentException("Invalid side found as: " + object.getSide() + ". Side can be only one of " + Side.toStringList());
        }
    }

    @Override
    public void update(JsonObject object) {
        if (isRightObject(object)) {
            if (!rightObjects.containsKey(object.getId())) {
                throw new IllegalArgumentException(object.getId() + " is not found!");
            } else rightObjects.replace(object.getId(), object);
        } else if (isLeftObject(object)) {
            if (!leftObjects.containsKey(object.getId())) {
                throw new IllegalArgumentException(object.getId() + " is not found!");
            } else leftObjects.replace(object.getId(), object);
        } else {
            throw new IllegalArgumentException("Invalid side found as: " + object.getSide() + ". Side can be only one of " + Side.toStringList());
        }
    }

    @Override
    public List<JsonObject> getObjects(String id) {
        List<JsonObject> jsonObjects = new ArrayList<>();
        jsonObjects.add(rightObjects.get(id));
        jsonObjects.add(leftObjects.get(id));
        return jsonObjects;
    }

    @Override
    public JsonObject getLeftObject(String id) {
        return leftObjects.get(id);
    }

    @Override
    public JsonObject getRightObject(String id) {
        return rightObjects.get(id);
    }

    private boolean isRightObject(JsonObject object) {
        return Side.RIGHT.equals(object.getSide());
    }

    private boolean isLeftObject(JsonObject object) {
        return Side.LEFT.equals(object.getSide());
    }
}
