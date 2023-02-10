package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.parsers.Factory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static final String ADDED = "added";
    public static final String MISSING = "missing";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";
    public static final String EMPTY_VALUE = " ";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, String> originalMap = getData(filePath1);
        Map<String, String> comparedMap = getData(filePath2);

        Set<String> keys = new TreeSet<>();
        keys.addAll(originalMap.keySet());
        keys.addAll(comparedMap.keySet());

        List<Map<String, String>> result = new ArrayList<>();

        for (String key : keys) {
            String value1 = originalMap.get(key);
            String value2 = comparedMap.get(key);

            if (!originalMap.containsKey(key)) {
                result.add(Map.of(OLD_VALUE, EMPTY_VALUE, NEW_VALUE, value2, ADDED, key));
            } else if (!comparedMap.containsKey(key)) {
                result.add(Map.of(MISSING, key, OLD_VALUE, value1, NEW_VALUE, EMPTY_VALUE));
            } else if (originalMap.containsKey(key) && comparedMap.containsKey(key)) {
                if (value1.equalsIgnoreCase(value2)) {
                    result.add(Map.of(UNCHANGED, key, OLD_VALUE, value1, NEW_VALUE, value2));
                } else {
                    result.add(Map.of(CHANGED, key, OLD_VALUE, value1, NEW_VALUE, value2));
                }
            }
        }

        return Stylish.format(result);
    }

    private static Map<String, String> getData(String filePath) throws Exception {
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
