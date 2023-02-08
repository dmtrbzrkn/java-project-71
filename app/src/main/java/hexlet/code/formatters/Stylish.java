package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.*;

public class Stylish {
    public static String format(List<Map<String, String>> result) {
        StringBuilder stylish = new StringBuilder("{\n");
        for (Map<String, String> entry : result) {
            for (Object key : entry.keySet()) {
                if (key.equals(ADDED)) {
                    stylish.append(" +").append(entry.get(ADDED)).append(": ").append(entry.get(NEW_VALUE)).append("\n");
                } else if (key.equals(MISSING)) {
                    stylish.append(" -").append(entry.get(MISSING)).append(": ").append(entry.get(OLD_VALUE)).append("\n");
                } else if (key.equals(UNCHANGED)) {
                    stylish.append("  ").append(entry.get(UNCHANGED)).append(": ").append(entry.get(OLD_VALUE)).append("\n");
                } else if (key.equals(CHANGED)) {
                    stylish.append(" -").append(entry.get(CHANGED)).append(": ").append(entry.get(OLD_VALUE)).append("\n");
                    stylish.append(" +").append(entry.get(CHANGED)).append(": ").append(entry.get(NEW_VALUE)).append("\n");
                }
            }
        }
        stylish.append("}");

        return stylish.toString();
    }
}
