package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Format {
    public static String formatSelection(List<Map<String, Object>> diff, String outPutFormat) throws Exception {
        switch (outPutFormat.toUpperCase()) {
            case "STYLISH" -> {
                return Stylish.format(diff);
            }
            case "PLAIN" -> {
                return Plain.format(diff);
            }
            case "JSON" -> {
                return JSON.format(diff);
            }
            default -> throw new Exception("Unknown format for output to the screen: " + outPutFormat);
        }
    }
}
