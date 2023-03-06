package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.formatters.Format.Formats.STYLISH;
import static hexlet.code.formatters.Format.Formats.PLAIN;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {
    final String jsonTestFilePath1 = "src/test/resources/TestJSON1.json";
    final String jsonTestFilePath2 = "src/test/resources/TestJSON2.json";
    final String ymlTestFilePath1 = "src/test/resources/TestYML1.yml";
    final String ymlTestFilePath2 = "src/test/resources/TestYML2.yml";

    public DifferTest() throws IOException {

    }

    private final String expectedStylishResult = Files.readString(Path.
            of("src/test/resources/ExpectedStylish.txt"));
    private final String expectedPlainResult = Files.readString(Path.
            of("src/test/resources/ExpectedPlay.txt"));

    @Test
    void testGenerateJSONToStylishOutputFormat() throws Exception {
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2, STYLISH);
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testGenerateYMLToStylishOutPutFormat() throws Exception {

        String actualResult = Differ.generate(ymlTestFilePath1, ymlTestFilePath2, STYLISH);
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testGenerateJSONToPlainOutputFormat() throws Exception {
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2, PLAIN);
        assertThat(actualResult).isEqualTo(expectedPlainResult);
    }

    @Test
    void testGenerateYMLToPlainOutputFormat() throws Exception {
        String actualResult = Differ.generate(ymlTestFilePath1, ymlTestFilePath2, PLAIN);
        assertThat(actualResult).isEqualTo(expectedPlainResult);
    }
}
