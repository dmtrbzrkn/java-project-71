package hexlet.code.formatters;


import hexlet.code.StatusChange;

import java.util.Map;

import static hexlet.code.StatusChange.ADDED;
import static hexlet.code.StatusChange.DELETED;
import static hexlet.code.StatusChange.CHANGED;
import static hexlet.code.StatusChange.UNCHANGED;

public class Stylish {
    public static String format(Map<String, StatusChange> differences) {
        StringBuilder stylish = new StringBuilder("{\n");
        for (Map.Entry<String, StatusChange> entry : differences.entrySet()) {
            String key = entry.getKey();
            String status = entry.getValue().getStatus();
            var oldValue = entry.getValue().getOldValue();
            var newValue = entry.getValue().getNewValue();

            switch (status) {
                case ADDED -> stylish.append("  + ").append(key).append(": ").append(newValue).append("\n");
                case DELETED -> stylish.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                case CHANGED -> {
                    stylish.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                    stylish.append("  + ").append(key).append(": ").append(newValue).append("\n");
                }
                case UNCHANGED -> stylish.append("    ").append(key).append(": ").append(oldValue).append("\n");
                default -> throw new RuntimeException("No such status: " + status);
            }
        }
        stylish.append("}");

        return stylish.toString();
    }
}
