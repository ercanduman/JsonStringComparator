package ercanduman.jsondifftask.data.enums;

import java.util.Arrays;

public enum Side {
    LEFT, RIGHT;

    public static String toStringList() {
        return Arrays.toString(values());
    }
}
