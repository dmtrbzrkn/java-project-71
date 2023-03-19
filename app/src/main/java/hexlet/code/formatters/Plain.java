package hexlet.code.formatters;

import java.util.Map;
import java.util.List;

import static hexlet.code.Tree.ADDED;
import static hexlet.code.Tree.DELETED;
import static hexlet.code.Tree.CHANGED;
import static hexlet.code.Tree.OLD_VALUE;
import static hexlet.code.Tree.NEW_VALUE;


public class Plain {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> map : differences) {
            for (String key : map.keySet()) {
                Object o = map.get(key);
                if (o.equals(ADDED)) {
                    result.append("Property " + "'").append(key)
                            .append("'").append(" was added with value: ").append(isComposite(map.get(NEW_VALUE)))
                            .append("\n");
                } else if (o.equals(DELETED)) {
                    result.append("Property " + "'").append(key)
                            .append("'").append(" was removed").append("\n");
                } else if (o.equals(CHANGED)) {
                    result.append("Property " + "'").append(key).append("'")
                            .append(" was updated. ").append("From ").append(isComposite(map.get(OLD_VALUE)))
                            .append(" to ").append(isComposite(map.get(NEW_VALUE))).append("\n");
                }
            }
        }
        return result.toString().trim();
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
