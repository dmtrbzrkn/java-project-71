package hexlet.code.formatters;

import hexlet.code.Tree;
import hexlet.code.Tree.Status;

import java.util.Map;

public class JSON {
    public static String format(Map<String, Object> data1, Map<String, Object> data2,
                                Map<String, Tree.Status> differences) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        stringBuilder.append("\n");

        for (Map.Entry<String, Status> element : differences.entrySet()) {

            switch (element.getValue()) {
                case DELETED, UNCHANGED -> stringBuilder.append("  \"").append(element.getKey()).append("=")
                        .append(data1.get(element.getKey())).append("  \"")
                        .append(": ").append("\"").append(element.getValue())
                        .append("\"").append(",").append("\n");
                case ADDED -> stringBuilder.append("  \"").append(element.getKey()).append("=")
                        .append(data2.get(element.getKey())).append("\"").append(": ")
                        .append("  \"").append(element.getValue()).append("\"")
                        .append(",").append("\n");
                case CHANGED -> stringBuilder.append("  \"").append(element.getKey()).append("=")
                        .append(data1.get(element.getKey())).append("\"").append(": ")
                        .append("  \"DELETED\"").append(",").append("\n")
                        .append("  \"").append(element.getKey()).append("=")
                        .append(data2.get(element.getKey())).append("\"")
                        .append(": ").append("  \"ADDED\"").append(",").append("\n");
                default -> throw new Exception("Something wrong!");
            }
        }

        var resultLength = stringBuilder.length();
        stringBuilder.delete(resultLength - 2, resultLength - 1);
        stringBuilder.append("}" + "\n");

        return stringBuilder.toString();
    }
}
