package ercanduman.jsondifftask.data.enums;

import java.util.Arrays;

/**
 * Defines the type of object that provided by Rest Controller.
 */
public enum Side {
    LEFT, RIGHT;

    public static String toStringList() {
        return Arrays.toString(values());
    }
}
