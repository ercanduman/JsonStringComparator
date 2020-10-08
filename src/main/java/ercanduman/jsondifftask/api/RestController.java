package ercanduman.jsondifftask.api;

import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import ercanduman.jsondifftask.service.JsonObjectService;
import ercanduman.jsondifftask.utils.JsonComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * {@link RestController} is the place where all HTTP requests are handled.
 * <p>
 * Handles POST requests for /left and /right endpoints and stores
 * executed JSON data into in-memory database({@link java.util.HashMap})
 * <p>
 * Handles GET requests for Constants.BASE_URL ("/v1/diff/{id}") by
 * returning result message which can be one of messages available
 * in {@link Constants} class.
 * <p>
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(Constants.BASE_URL)
public class RestController {
    @Autowired
    private final JsonObjectService service;

    public RestController(JsonObjectService service) {
        this.service = service;
    }

    /**
     * Returns result message by comparing two {@link JsonObject} via {@link JsonComparator}
     *
     * @param id parameter value and object id
     * @return result message
     */
    @GetMapping(produces = "application/json")
    public String result(@PathVariable("id") String id) {
        return JsonComparator.compare(service.getLeftObject(id), service.getRightObject(id));
    }

    /**
     * Handles POST requests and stores passed JSON data into {@link JsonObject}
     * by appending enum value of LEFT from {@link Side} enum class.
     *
     * @param id   parameter value and object id
     * @param json JSON data to be stored and used
     */
    @PostMapping(Constants.URL_LEFT)
    public void insertLeft(@PathVariable("id") String id, @RequestBody String json) {
        JsonObject object = new JsonObject(id, json, Side.LEFT);
        service.insertLeft(object);
    }

    /**
     * Handles POST requests and stores passed JSON data into {@link JsonObject}
     * by appending enum value of RIGHT from {@link Side} enum class.
     *
     * @param id   parameter value and object id
     * @param json JSON data to be stored and used
     */
    @PostMapping(Constants.URL_RIGHT)
    public void insertRight(@PathVariable("id") String id, @RequestBody String json) {
        JsonObject object = new JsonObject(id, json, Side.RIGHT);
        service.insertRight(object);
    }
}
