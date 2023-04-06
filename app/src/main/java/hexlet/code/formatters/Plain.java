package hexlet.code.formatters;

import hexlet.code.StatusChange;

import java.util.Formatter;
import java.util.Map;
import java.util.List;

import static hexlet.code.StatusChange.ADDED;
import static hexlet.code.StatusChange.DELETED;
import static hexlet.code.StatusChange.CHANGED;
import static hexlet.code.StatusChange.UNCHANGED;

public class Plain {
    public static String format(Map<String, StatusChange> differences) {
        StringBuilder stylish = new StringBuilder();
        Formatter formatter = new Formatter(stylish);

        for (Map.Entry<String, StatusChange> entry : differences.entrySet()) {

            String key = entry.getKey();
            StatusChange value = entry.getValue();

            switch (value.getStatus()) {
                case ADDED -> {
                    String addedValue = toStringRepresentation(value.getNewValue());
                    formatter.format("Property '%s' was added with value: %s%n", key, addedValue);
                }
                case DELETED -> formatter.format("Property '%s' was removed%n", key);
                case UNCHANGED -> {

                }
                case CHANGED -> {
                    String oldValue = toStringRepresentation(value.getOldValue());
                    String newValue = toStringRepresentation(value.getNewValue());
                    formatter.format("Property '%s' was updated. From %s to %s%n", key, oldValue, newValue);
                }
                default -> throw new IllegalStateException("No such status: " + value.getStatus());
            }
        }
        return stylish.toString().trim();
    }

    public static String toStringRepresentation(Object value) {
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
