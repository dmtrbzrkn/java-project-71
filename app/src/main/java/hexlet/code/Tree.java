package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

import static hexlet.code.StatusChange.ADDED;
import static hexlet.code.StatusChange.DELETED;
import static hexlet.code.StatusChange.CHANGED;
import static hexlet.code.StatusChange.UNCHANGED;

public class Tree {

    public static Map<String, StatusChange> genDiff(Map<String, Object> firstFileData,
                                                    Map<String, Object> secondFileData) {
        Map<String, StatusChange> result = new TreeMap<>();

        Set<String> keys = new TreeSet<>(firstFileData.keySet());
        keys.addAll(secondFileData.keySet());

        for (String key : keys) {
            Object firstFileValue = firstFileData.get(key);
            Object secondFileValue = secondFileData.get(key);

            if (!firstFileData.containsKey(key)) {
                result.put(key, new StatusChange(ADDED, secondFileValue));
            } else if (!secondFileData.containsKey(key)) {
                result.put(key, new StatusChange(DELETED, firstFileValue));
            } else if (firstFileData.containsKey(key) && secondFileData.containsKey(key)) {
                if (isEquals(firstFileValue, secondFileValue)) {
                    result.put(key, new StatusChange(UNCHANGED, secondFileValue));
                } else {
                    result.put(key, new StatusChange(CHANGED, firstFileValue, secondFileValue));
                }
            }
        }
        return result;
    }

    private static boolean isEquals(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        } else if (value1 == null || value2 == null) {
            return false;
        } else {
            return Objects.equals(value1, value2);
        }
    }
}
