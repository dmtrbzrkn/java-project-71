package hexlet.code.formatters;

import hexlet.code.Tree.Status;

import java.util.Map;

public class Format {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON_FORMAT = "json";

    public static String formatSelection(Map<String, Object> data1, Map<String, Object> data2,
                                         Map<String, Status> differences, String format) throws Exception {
        switch (format) {
            case "stylish" -> {
                return Stylish.format(data1, data2, differences);
            }
            case "plain" -> {
                return Plain.format(data1, data2, differences);
            }
            case "json" -> {
                return JSON.format(data1, data2, differences);
            }
            default -> throw new Exception("Unknown format for output to the screen: " + format);
        }
    }
}
