package ercanduman.jsondifftask.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ercanduman.jsondifftask.Constants;
import ercanduman.jsondifftask.data.entity.JsonObject;
import ercanduman.jsondifftask.data.enums.Side;
import ercanduman.jsondifftask.data.service.JsonObjectService;
import ercanduman.jsondifftask.utils.JsonComparator;
import ercanduman.jsondifftask.utils.JsonResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * {@link RestController} is the place where all HTTP requests are handled.
 * <p>
 * Handles POST requests for /left and /right endpoints and stores
 * executed JSON data into in-memory database via using {@link JsonObjectService}
 * <p>
 * Handles GET requests for Constants.BASE_URL ("/v1/diff/{id}") by
 * returning result messages which can be one of the messages available
 * in {@link Constants} class.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(Constants.BASE_URL)
public class RestController {
    @Autowired
    private final JsonObjectService service;
    @Autowired
    private final JsonComparator comparator;

    public RestController(JsonObjectService service, JsonComparator comparator) {
        this.service = service;
        this.comparator = comparator;
    }

    /**
     * Handles POST requests and stores passed JSON data into {@link JsonObject}
     * by appending enum value of LEFT from {@link Side} enum class.
     * <p>
     * Calls {@link JsonObjectService} and inserts a new {@link JsonObject} into database.
     *
     * @param id   parameter value and object id
     * @param json JSON data to be stored and used
     * @return Json string message
     */
    @PostMapping(value = Constants.URL_LEFT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertLeft(@PathVariable("id") String id, @RequestBody String json) {
        try {
            String jsonData = isJsonValid(json);
            JsonObject object = new JsonObject(id, jsonData, Side.LEFT);
            service.insertLeft(object);
            return JsonResponseCreator.response(false, Constants.EXC_RESPONSE_INSERTED, null);
        } catch (JsonParseException | IOException e) {
            return JsonResponseCreator.response(true, "Invalid JSON found! Error: " + e.getMessage(), null);
        }
    }

    /**
     * Handles POST requests and stores passed JSON data into {@link JsonObject}
     * by appending enum value of RIGHT from {@link Side} enum class.
     * <p>
     * Calls {@link JsonObjectService} and inserts a new {@link JsonObject} into database.
     *
     * @param id   parameter value and object id
     * @param json JSON data to be stored and used
     * @return Json string message
     */
    @PostMapping(value = Constants.URL_RIGHT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertRight(@PathVariable("id") String id, @RequestBody String json) {
        try {
            String jsonData = isJsonValid(json);
            JsonObject object = new JsonObject(id, jsonData, Side.RIGHT);
            service.insertRight(object);
            return JsonResponseCreator.response(false, Constants.EXC_RESPONSE_INSERTED, null);
        } catch (JsonParseException | IOException e) {
            return JsonResponseCreator.response(true, "Invalid JSON found! Error: " + e.getMessage(), null);
        }
    }

    /**
     * Checks whether input text is valid JSON or not.
     *
     * @param json input text from client
     * @return valid JSON string
     * @throws IOException If not valid JSON then throws exception with message
     */
    private String isJsonValid(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);

        JsonNode jsonObj = mapper.readTree(mapper.getFactory().createParser(json));

        // System.out.println(jsonObj.toString());
        // System.out.println("JSONDATA: " + jsonObj.toString());
        return jsonObj.toString();
    }

    /**
     * Returns result message by comparing two {@link JsonObject} via {@link JsonComparator}
     *
     * @param id parameter value and object id
     * @return result message
     */
    @GetMapping(produces = "application/json")
    public String result(@PathVariable("id") String id) {
        return comparator.compare(service.getLeftObject(id), service.getRightObject(id));
    }
}
