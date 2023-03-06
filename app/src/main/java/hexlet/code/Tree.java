package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Tree {
    public enum Status {
        ADDED,
        DELETED,
        CHANGED,
        UNCHANGED
    }

    public static Map<String, Status> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Status> result = new TreeMap<>();

        Set<String> keys = new HashSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (!data1.containsKey(key)) {
                result.put(key, Status.ADDED);
            } else if (!data2.containsKey(key)) {
                result.put(key, Status.DELETED);
            } else {
                if (Objects.equals(data2.get(key), data1.get(key))) {
                    result.put(key, Status.UNCHANGED);
                } else {
                    result.put(key, Status.CHANGED);
                }
            }
        }
        return result;
    }
}
