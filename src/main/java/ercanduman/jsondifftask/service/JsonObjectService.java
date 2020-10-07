package ercanduman.jsondifftask.service;

import ercanduman.jsondifftask.data.dao.JsonObjectDao;
import ercanduman.jsondifftask.data.entity.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonObjectService {
    @Autowired
    private JsonObjectDao dao;

    public JsonObjectService(JsonObjectDao dao) {
        this.dao = dao;
    }

    void insert(JsonObject object) {
        dao.insert(object);
    }

    void update(JsonObject object) {
        dao.update(object);
    }

    List<JsonObject> getObjects(String id) {
        return dao.getObjects(id);
    }
}
