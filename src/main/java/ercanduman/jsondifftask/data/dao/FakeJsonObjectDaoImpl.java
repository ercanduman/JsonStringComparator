package ercanduman.jsondifftask.data.dao;

import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Fake implementation of Data access object (DAO)
 * <p>
 * This object overrides all {@link JsonObjectDao} pattern methods.
 * <p>
 * Uses {@link HashMap} in order to work as in-memory database.
 * <p>
 * Works as repository which stores all objects that provided by service
 */
@Repository
public class FakeJsonObjectDaoImpl implements JsonObjectDao {
    private final HashMap<String, JsonObject> leftObjects = new HashMap<>();
    private final HashMap<String, JsonObject> rightObjects = new HashMap<>();

    /**
     * Inserts object which has LEFT attribute into in-memory database.
     *
     * @param object {@link JsonObject} provided by service
     */
    @Override
    public void insertLeft(JsonObject object) {
        if (object == null) throw new IllegalArgumentException("Object passed as NULL!");
        if (isLeftObject(object)) leftObjects.put(object.getId(), object);
        else {
            throw new IllegalArgumentException("Invalid side found as: " + object.getSide() + ". Side can be only be " + Side.LEFT + " for /left endpoint.");
        }
    }

    /**
     * Inserts object which has RIGHT attribute into in-memory database.
     *
     * @param object {@link JsonObject} provided by service
     */
    @Override
    public void insertRight(JsonObject object) {
        if (object == null) throw new IllegalArgumentException("Object passed as NULL!");
        if (isRightObject(object)) rightObjects.put(object.getId(), object);
        else {
            throw new IllegalArgumentException("Invalid side found as: " + object.getSide() + ". Side can be only be " + Side.RIGHT + " for /right endpoint.");
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

    /**
     * Returns {@link JsonObject} to which the specified object is mapped,
     * or null if this map contains no mapping for the object id.
     *
     * @param id object id
     * @return {@link JsonObject} stored previously with object id
     */
    @Override
    public JsonObject getLeftObject(String id) {
        return leftObjects.get(id);
    }

    /**
     * Returns {@link JsonObject} to which the specified object is mapped,
     * or null if this map contains no mapping for the object id.
     *
     * @param id object id
     * @return {@link JsonObject} stored previously with object id
     */
    @Override
    public JsonObject getRightObject(String id) {
        return rightObjects.get(id);
    }

    /**
     * Check whether {@link JsonObject} is has RIGHT attribute
     *
     * @param object {@link JsonObject} passed by service
     * @return boolean
     */
    private boolean isRightObject(JsonObject object) {
        return Side.RIGHT.equals(object.getSide());
    }

    /**
     * Check whether {@link JsonObject} is has LEFT attribute
     *
     * @param object {@link JsonObject} passed by service
     * @return boolean
     */
    private boolean isLeftObject(JsonObject object) {
        return Side.LEFT.equals(object.getSide());
    }
}
