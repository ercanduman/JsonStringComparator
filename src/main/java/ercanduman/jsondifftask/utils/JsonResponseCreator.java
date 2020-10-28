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

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Generates JSON message based on given parameters.
     *
     * @param isError     boolean value which can be true or false.
     * @param message     text message
     * @param differences {@link ercanduman.jsondifftask.utils.JsonComparator.Difference} objects provided from {@link JsonComparator} object
     * @return json string message
     */
    public String response(boolean isError, String message, String differences) {
        StringBuilder builder = new StringBuilder("{");
        builder.append("\"error\":").append(isError)
                .append(",\"message\":\"").append(message);

        if (differences != null) {
            builder.append("\",").append("\"differences\":").append(differences);
            builder.append("}");
        } else builder.append("\"}");
        return builder.toString();
    }
}
