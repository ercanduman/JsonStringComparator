package ercanduman.jsondifftask.utils;

import org.springframework.stereotype.Component;

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
@Component
public class JsonResponseCreator {
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
        builder.append("\"error\": ").append(isError)
                .append(",\"message\":\"").append(message);

        if (differences != null) {
            builder.append("\",").append("\"Differences\": ").append(differences);
            builder.append("}");
        } else builder.append("\"}");
        return builder.toString();
    }
}
