package hexlet.code.formatters;

import hexlet.code.Tree.Status;

import java.util.Map;

public class Format {
    public enum Formats {
        STYLISH,
        PLAIN,
        JSON

    }

    public static String formatSelection(Map<String, Object> data1, Map<String, Object> data2,
                                         Map<String, Status> differences, Formats format) throws Exception {
        switch (format) {
            case STYLISH -> {
                return Stylish.format(data1, data2, differences);
            }
            case PLAIN -> {
                return Plain.format(data1, data2, differences);
            }
            case JSON -> {
                return JSON.format(data1, data2, differences);
            }
            default -> throw new Exception("Unknown format for output to the screen: " + format);
        }
    }
}
