package hexlet.code;

import hexlet.code.parsers.Parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {
    public static final String DEFAULT_OUTPUT_FORMAT = "default";

    public static String generate(String filePath1, String filePath2, String outputFormat) throws Exception {
        Map<String, Object> originalMap = getData(filePath1);
        Map<String, Object> comparedMap = getData(filePath2);

        Set<String> keys = new TreeSet<>(originalMap.keySet());
        keys.addAll(comparedMap.keySet());

        if (keys.isEmpty()) {
            return "{}";
        }

        Map<String, Map<String, Object[]>> result = new TreeMap<>();

        return "";
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, DEFAULT_OUTPUT_FORMAT);
    }

    private static Map getData(String filePath) throws Exception {
        Path path = Path.of(filePath).toAbsolutePath().normalize();
        if (Files.notExists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String contents = Files.readString(path);
        return Parser.parse(contents);
    }

    private static String getDataFormat(String pathToFile) {
        return pathToFile.substring(pathToFile.lastIndexOf('.') + 1);
    }
}
