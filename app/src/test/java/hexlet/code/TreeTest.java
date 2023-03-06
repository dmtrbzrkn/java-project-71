package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class TreeTest {
    private Map<String, Tree.Status> expectations = new TreeMap<>();
    private Map<String, Tree.Status> result = new TreeMap<>();
    private Map<String, Object> data1 = new TreeMap<>();
    private Map<String, Object> data2 = new TreeMap<>();

    @BeforeEach
    public final void clearMaps() {
        data1.clear();
        data2.clear();
        expectations.clear();
        result.clear();
    }

    @Test
    public void testFirstEmptyMap() {
        data2.put("two", "own");
        expectations.put("two", Tree.Status.ADDED);
        result = Tree.genDiff(data1, data2);
        assertThat(expectations).isEqualTo(result);
    }

    @Test
    public void testSecondEmptyMap() {

        data1.put("one", "eon");
        expectations.put("one", Tree.Status.DELETED);
        result = Tree.genDiff(data1, data2);
        assertThat(expectations).isEqualTo(result);
    }

    @Test
    public void testMapDiff1() {

        data1.put("three", "eerht");
        data2.put("four", "ruof");

        expectations.put("three", Tree.Status.DELETED);
        expectations.put("four", Tree.Status.ADDED);

        result = Tree.genDiff(data1, data2);

        assertThat(expectations).isEqualTo(result);
    }

    @Test
    public void testMapDiff2() {

        data1.put("five", "5");
        data1.put("six", "6");

        data2.put("six", "xis");
        data2.put("five", "5");

        expectations.put("six", Tree.Status.CHANGED);
        expectations.put("five", Tree.Status.UNCHANGED);

        result = Tree.genDiff(data1, data2);

        assertThat(expectations).isEqualTo(result);

    }

    @Test
    public void testMapDiff3() {

        data1.put("seven", "neves");
        data2.put("eighth", "True");

        expectations.put("seven", Tree.Status.DELETED);
        expectations.put("eighth", Tree.Status.ADDED);

        result = Tree.genDiff(data1, data2);

        assertThat(expectations).isEqualTo(result);

    }
}
