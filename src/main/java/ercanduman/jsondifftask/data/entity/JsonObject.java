package ercanduman.jsondifftask.data.entity;

import ercanduman.jsondifftask.data.enums.Side;

/**
 * Data class to store processed data
 */
public class JsonObject {
    private final String id;
    private final String content;
    private final Side side;

    public JsonObject(String id, String content, Side side) {
        this.id = id;
        this.content = content;
        this.side = side;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", side=" + side +
                '}';
    }
}

