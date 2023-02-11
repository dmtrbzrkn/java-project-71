package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.parsers.Factory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static final String ADDED = "added";
    public static final String MISSING = "missing";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";
    public static final String EMPTY_VALUE = " ";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> originalMap = getData(filePath1);
        System.out.println(originalMap);
        Map<String, Object> comparedMap = getData(filePath2);
        System.out.println(comparedMap);

        Set<String> keys = new TreeSet<>();
        keys.addAll(originalMap.keySet());
        keys.addAll(comparedMap.keySet());
        System.out.println(keys);

        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : keys) {
            var value1 = originalMap.get(key);
            System.out.println(value1);
            var value2 = comparedMap.get(key);
            System.out.println(value2);

            if (!originalMap.containsKey(key)) {
                result.add(Map.of(OLD_VALUE, EMPTY_VALUE, NEW_VALUE, value2, ADDED, key));
                System.out.println(result);
            } else if (!comparedMap.containsKey(key)) {
                result.add(Map.of(MISSING, key, OLD_VALUE, value1, NEW_VALUE, EMPTY_VALUE));
                System.out.println(result);
            } else if (originalMap.containsKey(key) && comparedMap.containsKey(key)) {
                if (isEquals(value1, value2)) {
                    result.add(Map.of(UNCHANGED, key, OLD_VALUE, value1, NEW_VALUE, value2));
                    System.out.println(result);
                } else {
                    result.add(Map.of(CHANGED, key, OLD_VALUE, value1, NEW_VALUE, value2));
                    System.out.println(result);
                }
            }
        }
        System.out.println(result);
        return Stylish.format(result);
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

    private static boolean isEquals(Object value1, Object value2) {
        if (Objects.nonNull(value1) && Objects.equals(value1, value2)) {
            return true;
        } else if (Objects.nonNull(value2) && !Objects.equals(value1, value2)) {
            return false;
        } else {
            return false;
        }
    }
}
