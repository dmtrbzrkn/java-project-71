package hexlet.code.formatters;

import java.util.Formatter;
import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.ADDED;
import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.MISSING;
import static hexlet.code.Differ.NEW_VALUE;
import static hexlet.code.Differ.OLD_VALUE;

public class Plain {
    public static String format(List<Map<String, Object>> result) {
        StringBuilder output = new StringBuilder();
        Formatter formatter = new Formatter(output);
        for (Map<String, Object> entry : result) {
            for (Object key : entry.keySet()) {
                if (key.equals(ADDED)) {
                    String addedValue = isComposite(entry.get(NEW_VALUE));
                    formatter.format("Property '%s' was added with value: %s%n", entry.get(ADDED), addedValue);
                } else if (key.equals(MISSING)) {
                    formatter.format("Property '%s' was removed%n", entry.get(MISSING));
                } else if (key.equals(CHANGED)) {
                    String removedValue = isComposite(entry.get(OLD_VALUE));
                    String addedValue = isComposite(entry.get(NEW_VALUE));
                    formatter.format("Property '%s' was updated. From %s to %s%n",
                            entry.get(CHANGED),
                            removedValue,
                            addedValue);
                }
            }
        }
        return output.toString().trim();
    }

    public static String isComposite(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
