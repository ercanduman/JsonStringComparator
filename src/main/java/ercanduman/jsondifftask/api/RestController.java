package ercanduman.jsondifftask.api;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import ercanduman.jsondifftask.service.JsonObjectService;
import ercanduman.jsondifftask.utils.JsonComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(Constants.BASE_URL)
public class RestController {
    @Autowired
    private JsonObjectService service;

    public RestController(JsonObjectService service) {
        this.service = service;
    }

/*    @GetMapping()
    public List<JsonObject> objects(@PathVariable("id") String id) {
        return service.getObjects(id);
    }*/

    @GetMapping(produces = "application/json")
    public String result(@PathVariable("id") String id) {
        return JsonComparator.compare(service.getLeftObject(id), service.getRightObject(id));
    }

    @PostMapping(Constants.URL_LEFT)
    public void insertLeft(@PathVariable("id") String id, @RequestBody String json) {
        JsonObject object = new JsonObject(id, json, Side.LEFT);
        service.insertLeft(object);
    }

    @PostMapping(Constants.URL_RIGHT)
    public void insertRight(@PathVariable("id") String id, @RequestBody String json) {
        JsonObject object = new JsonObject(id, json, Side.RIGHT);
        service.insertRight(object);
    }

}
