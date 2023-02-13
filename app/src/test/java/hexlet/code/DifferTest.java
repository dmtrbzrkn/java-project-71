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
    private final String expectedPlainResult = Files.readString(Path.
            of("src/test/resources/ExpectedPlay.txt"));

    @Test
    void testGenerateJSONToStylishOutputFormat() throws Exception {
        String jsonTestFilePath1 = "src/test/resources/TestJSON1.json";
        String jsonTestFilePath2 = "src/test/resources/TestJSON2.json";
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2, "stylish");
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testGenerateYMLToStylishOutPutFormat() throws Exception {
        String ymlTestFilePath1 = "src/test/resources/TestYML1.yml";
        String ymlTestFilePath2 = "src/test/resources/TestYML2.yml";
        String actualResult = Differ.generate(ymlTestFilePath1, ymlTestFilePath2, "stylish");
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testGenerateJSONToPlainOutputFormat() throws Exception {
        String jsonTestFilePath1 = "src/test/resources/TestJSON1.json";
        String jsonTestFilePath2 = "src/test/resources/TestJSON2.json";
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2, "plain");
        assertThat(actualResult).isEqualTo(expectedPlainResult);
    }

    @Test
    void testGenerateYMLToPlainOutputFormat() throws Exception {
        String ymlTestFilePath1 = "src/test/resources/TestYML1.yml";
        String ymlTestFilePath2 = "src/test/resources/TestYML2.yml";
        String actualResult = Differ.generate(ymlTestFilePath1, ymlTestFilePath2, "plain");
        assertThat(actualResult).isEqualTo(expectedPlainResult);
    }
}
