package ercanduman.jsondifftask.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

/**
 * Generates JSON messages for requests
 * <p>
 * i.e. If insertion request is successful then below json message will be generated.
 * <p>
 * ```
 * {
 * "error": false,
 * "message": "Inserted successfully."
 * }
 * ```
 * <p>
 * <p>
 * Generates JSON message based on given parameters.
 * <p>
 * error     boolean value which can be true or false.
 * message     text message
 * differences {@link ercanduman.jsondifftask.utils.JsonComparator.Difference} objects provided from {@link JsonComparator} object
 * returns json string message
 */
@JsonComponent
@JsonPropertyOrder({"error", "message", "differences"})
public class JsonResponseCreator {
    private boolean error;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<JsonComparator.Difference> differences;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JsonComparator.Difference> getDifferences() {
        return differences;
    }

    public void setDifferences(List<JsonComparator.Difference> differences) {
        this.differences = differences;
    }

    /**
     * Generates JSON message based on given parameters.
     *
     * @return json string message
     */
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
