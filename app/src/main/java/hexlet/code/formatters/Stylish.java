package hexlet.code.formatters;

import hexlet.code.Tree;
import hexlet.code.Tree.Status;

import java.util.Map;

public class Stylish {
    public static String format(Map<String, Object> data1, Map<String, Object> data2,
                                Map<String, Tree.Status> differences) {
        StringBuilder stringBuilder = new StringBuilder("{\n");

        for (Map.Entry<String, Status> element : differences.entrySet()) {
            switch (element.getValue()) {
                case ADDED -> stringBuilder.append("  + ").append(element.getKey()).append(": ")
                        .append(data2.get(element.getKey())).append("\n");
                case DELETED -> stringBuilder.append("  - ").append(element.getKey())
                        .append(": ").append(data1.get(element.getKey())).append("\n");
                case CHANGED -> stringBuilder.append("    ").append(element.getKey())
                        .append(": ").append(data1.get(element.getKey())).append("\n");
                case UNCHANGED -> stringBuilder.append("  - ").append(element.getKey()).append(": ")
                        .append(data2.get(element.getKey())).append("\n").append("  + ")
                        .append(element.getKey()).append(": ").append(data2.get(element.getKey())).append("\n");
                default -> {
                    return "Something went wrong!";
                }
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
