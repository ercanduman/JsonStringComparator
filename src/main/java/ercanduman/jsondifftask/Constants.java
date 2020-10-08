package ercanduman.jsondifftask;

/**
 * {@link Constants} class contains application constants.
 */
public class Constants {
    // Rest api url constants
    public static final String BASE_URL = "/v1/diff/{id}";
    public static final String URL_LEFT = "/left";
    public static final String URL_RIGHT = "/right";


    // Execution result constants
    public static final String EXC_RESULT_EQUAL = "Objects are equal";
    public static final String EXC_RESULT_DIFF_SIZE = "Objects have different sizes";
    public static final String EXC_RESULT_DIFF_OFFSET = "Objects have same sizes but differences. Difference length: %s \nDifferences: %s";

    public static final String RESULT_OBJECTS_NULL = "Both objects are NULL";
    public static final String RESULT_NULL_COMPARISON = "NULL comparison not allowed";
}
