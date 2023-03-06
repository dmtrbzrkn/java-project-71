package hexlet.code.formatters;

import hexlet.code.Tree;
import hexlet.code.Tree.Status;

import java.util.Map;
import java.util.List;


public class Plain {
    public static String format(Map<String, Object> data1, Map<String, Object> data2,
                                Map<String, Tree.Status> differences) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Status> element : differences.entrySet()) {
            var value1 = data1.get(element.getKey());
            var value2 = data2.get(element.getKey());

            var composite1 = isComposite(value1);
            var composite2 = isComposite(value2);

            var status = element.getValue();

            switch (status) {
                case ADDED -> stringBuilder.append("Property " + "'").append(element.getKey())
                        .append("'").append(" was added with value: ").append(composite2)
                        .append("\n");
                case DELETED -> stringBuilder.append("Property " + "'").append(element.getKey())
                        .append("'").append(" was removed").append("\n");
                case CHANGED -> stringBuilder.append("Property " + "'").append(element.getKey()).append("'")
                        .append(" was updated. ").append("From ").append(composite1)
                        .append(" to ").append(composite2).append("\n");
                case UNCHANGED -> {
                }
                default -> throw new Exception("Something wrong!");
            }
        }
        return stringBuilder.toString();
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
