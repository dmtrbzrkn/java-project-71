package hexlet.code.formatters;


import java.util.List;
import java.util.Map;

import static hexlet.code.Tree.ADDED;
import static hexlet.code.Tree.DELETED;
import static hexlet.code.Tree.CHANGED;
import static hexlet.code.Tree.UNCHANGED;
import static hexlet.code.Tree.OLD_VALUE;
import static hexlet.code.Tree.NEW_VALUE;

public class Stylish {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> map : differences) {
            for (String key : map.keySet()) {
                Object o = map.get(key);
                if (o.equals(ADDED)) {
                    result.append("  + ").append(key).append(": ").append(map.get(NEW_VALUE))
                            .append("\n");
                } else if (o.equals(DELETED)) {
                    result.append("  - ").append(key).append(": ").append(map.get(OLD_VALUE))
                            .append("\n");
                } else if (o.equals(CHANGED)) {
                    result.append("  - ").append(key).append(": ").append(map.get(OLD_VALUE))
                            .append("\n").append("  + ").append(key).append(": ")
                            .append(map.get(NEW_VALUE)).append("\n");
                } else if (o.equals(UNCHANGED)) {
                    result.append("    ").append(key).append(": ").append(map.get(OLD_VALUE))
                            .append("\n");
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
