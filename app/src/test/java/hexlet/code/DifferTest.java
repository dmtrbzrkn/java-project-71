package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {
    public DifferTest() throws IOException {

    }

    private final String expectedStylishResult = Files.readString(Path.
            of("src/test/resources/ExpectedStylish.txt"));

    @Test
    void testGenerateJSONToStylishOutputFormat() throws Exception {
        String jsonTestFilePath1 = "src/test/resources/TestJSON1.json";
        String jsonTestFilePath2 = "src/test/resources/TestJSON2.json";
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2, "stylish");
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testGenerateYMLToStylishOutPutFormat() throws Exception {
        String jsonTestFilePath1 = "src/test/resources/TestYML1.yml";
        String jsonTestFilePath2 = "src/test/resources/TestYML2.yml";
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2, "stylish");
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }
}
