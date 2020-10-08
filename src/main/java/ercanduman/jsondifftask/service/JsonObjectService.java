package ercanduman.jsondifftask.service;

import ercanduman.jsondifftask.data.dao.JsonObjectDao;
import ercanduman.jsondifftask.data.entity.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonObjectService {
    @Autowired
    private final JsonObjectDao dao;

    public JsonObjectService(JsonObjectDao dao) {
        this.dao = dao;
    }

    public void insertLeft(JsonObject object) {
        dao.insertLeft(object);
    }

    public void insertRight(JsonObject object) {
        dao.insertRight(object);
    }

    public JsonObject getLeftObject(String id) {
        return dao.getLeftObject(id);
    }

    public JsonObject getRightObject(String id) {
        return dao.getRightObject(id);
    }
}
