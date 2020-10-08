package ercanduman.jsondifftask.data.entity;

import ercanduman.jsondifftask.data.enums.Side;

/**
 * Data class to store and retrieve processed data
 */
public class JsonObject {
    private String id;
    private String content;
    private Side side;

    public JsonObject(String id, String content, Side side) {
        this.id = id;
        this.content = content;
        this.side = side;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "JsonObject {" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' + '}';
    }
}

