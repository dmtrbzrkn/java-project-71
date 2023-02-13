package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.parsers.Factory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Differ {
    public static final String ADDED = "added";
    public static final String MISSING = "missing";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";
    public static final String EMPTY_VALUE = " ";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";

    public static String generate(String filePath1, String filePath2, String outPutFormat) throws Exception {
        Map<String, Object> originalMap = getData(filePath1);
        Map<String, Object> comparedMap = getData(filePath2);

        Set<String> keys = new TreeSet<>();
        keys.addAll(originalMap.keySet());
        keys.addAll(comparedMap.keySet());

        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : keys) {
            var value1 = originalMap.get(key);
            var value2 = comparedMap.get(key);

            if (!originalMap.containsKey(key)) {
                Map<String, Object> diff = new HashMap<>();
                diff.put(ADDED, key);
                diff.put(OLD_VALUE, EMPTY_VALUE);
                diff.put(NEW_VALUE, value2);
                result.add(diff);
            } else if (!comparedMap.containsKey(key)) {
                Map<String, Object> diff = new HashMap<>();
                diff.put(MISSING, key);
                diff.put(OLD_VALUE, value1);
                diff.put(NEW_VALUE, EMPTY_VALUE);
                result.add(diff);
            } else if (originalMap.containsKey(key) && comparedMap.containsKey(key)) {
                Map<String, Object> diff = new HashMap<>();
                if (Objects.equals(value1, value2)) {
                    diff.put(UNCHANGED, key);
                } else {
                    diff.put(CHANGED, key);
                }
                diff.put(OLD_VALUE, value1);
                diff.put(NEW_VALUE, value2);
                result.add(diff);
            }
        }
        return Format.formatSelection(result, outPutFormat);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static Map<String, Object> getData(String filePath) throws Exception {
        Path path = Path.of(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String contents = Files.readString(path);
        var parser = Factory.getParser(getFileExtension(String.valueOf(path)));
        return parser.parse(contents);
    }

    private static String getFileExtension(String pathToFile) {
        return pathToFile.substring(pathToFile.indexOf(".") + 1);
    }
}
