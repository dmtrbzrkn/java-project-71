package hexlet.code;

import java.util.*;

public class Tree {
    public static final String ADDED = "added";
    public static final String DELETED = "deleted";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";

    public static List<Map<String, Object>> genDiff(Map<String, Object> firstFileData,
                                                    Map<String, Object> secondFileData) {
        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> keys = new TreeSet<>(firstFileData.keySet());
        keys.addAll(secondFileData.keySet());

        for (String key : keys) {
            Map<String, Object> diff = new TreeMap<>();
            if (!firstFileData.containsKey(key)) {
                diff.put(key, ADDED);
                diff.put(OLD_VALUE, firstFileData.get(key));
                diff.put(NEW_VALUE, secondFileData.get(key));
                result.add(diff);
            } else if (!secondFileData.containsKey(key)) {
                diff.put(key, DELETED);
                diff.put(OLD_VALUE, firstFileData.get(key));
                diff.put(NEW_VALUE, secondFileData.get(key));
                result.add(diff);
            } else if (firstFileData.containsKey(key) && secondFileData.containsKey(key)) {
                if (Objects.equals(firstFileData.get(key), secondFileData.get(key))) {
                    diff.put(key, UNCHANGED);
                } else {
                    diff.put(key, CHANGED);
                }
                diff.put(OLD_VALUE, firstFileData.get(key));
                diff.put(NEW_VALUE, secondFileData.get(key));
                result.add(diff);
            }
        }
        System.out.println(result);
        return result;
    }

//    private static String nullToString(Object object) {
//        if (object == null) {
//            return "null";
//        } else {
//            return object.toString();
//        }
//    }

}
