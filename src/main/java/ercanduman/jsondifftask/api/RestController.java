package ercanduman.jsondifftask.api;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.service.JsonObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(Constants.BASE_URL)
public class RestController {
    @Autowired
    private JsonObjectService service;

    public RestController(JsonObjectService service) {
        this.service = service;
    }

    @GetMapping()
    public List<JsonObject> objects(@PathVariable("id") String id) {
        return service.getObjects(id);
    }

    @PostMapping(Constants.URL_LEFT)
    public void insertLeft(@RequestBody JsonObject object) {
        service.insertLeft(object);
    }

    @PostMapping(Constants.URL_RIGHT)
    public void insertRight(@RequestBody JsonObject object) {
        service.insertRight(object);
    }

}
