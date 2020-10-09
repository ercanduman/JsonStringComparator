package ercanduman.jsondifftask.service;

import ercanduman.jsondifftask.data.dao.JsonObjectDao;
import ercanduman.jsondifftask.data.entity.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a Service which a gateway between {@link ercanduman.jsondifftask.api.RestController} and in-memory database {@link ercanduman.jsondifftask.data.dao.FakeJsonObjectDaoImpl}
 * <p>
 * Provides all implementations of CRUD operations
 */
@Service
public class JsonObjectService {
    @Autowired
    private final JsonObjectDao dao;

    public JsonObjectService(JsonObjectDao dao) {
        this.dao = dao;
    }

    /**
     * Inserts {@link JsonObject} into database which is provided from "/left" endpoint.
     *
     * @param object {@link JsonObject}
     */
    public void insertLeft(JsonObject object) {
        dao.insertLeft(object);
    }

    /**
     * Inserts {@link JsonObject} into database which is provided from "/right" endpoint.
     *
     * @param object {@link JsonObject}
     */
    public void insertRight(JsonObject object) {
        dao.insertRight(object);
    }

    /**
     * Retrieves data from database based on id field. Returns {@link JsonObject} which has Side.LEFT attribute
     * <p>
     * If object is not available in database then returns NULL.
     *
     * @param id unique id to provide data
     * @return {@link JsonObject} from database
     */
    public JsonObject getLeftObject(String id) {
        return dao.getLeftObject(id);
    }

    /**
     * Retrieves data from database based on id field. Returns {@link JsonObject} which has Side.RIGHT attribute
     * <p>
     * If object is not available in database then returns NULL.
     *
     * @param id unique id to provide data
     * @return {@link JsonObject} from database
     */
    public JsonObject getRightObject(String id) {
        return dao.getRightObject(id);
    }
}
