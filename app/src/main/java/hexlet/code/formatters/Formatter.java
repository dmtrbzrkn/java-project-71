package hexlet.code.formatters;


import hexlet.code.StatusChange;

import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON_FORMAT = "json";

    public static String formatSelection(Map<String, StatusChange> differences, String format) throws Exception {
        switch (format) {
            case STYLISH -> {
                return Stylish.format(differences);
            }
            case PLAIN -> {
                return Plain.format(differences);
            }
            case JSON_FORMAT -> {
                return JSON.format(differences);
            }
            default -> throw new Exception("Unknown format for output to the screen: " + format);
        }
    }
}
