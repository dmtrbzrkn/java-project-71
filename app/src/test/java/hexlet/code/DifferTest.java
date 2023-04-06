package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static hexlet.code.formatters.Formatter.STYLISH;
import static hexlet.code.formatters.Formatter.PLAIN;
import static hexlet.code.formatters.Formatter.JSON_FORMAT;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {
    private final String pathToResources = "src/test/resources";
    private final String jsonTestFilePath1 = pathToResources + "/TestJSON1.json";
    private final String jsonTestFilePath2 = pathToResources + "/TestJSON2.json";
    private final String ymlTestFilePath1 = pathToResources + "/TestYML1.yml";
    private final String ymlTestFilePath2 = pathToResources + "/TestYML2.yml";

    public DifferTest() {

    }

    private static String expectedStylishResult;
    private static String expectedPlainResult;
    private static String expectedJSONResult;

    @BeforeAll
    public static void beforeAll() throws IOException {
        expectedStylishResult = Files.readString(Path.
                of("src/test/resources/ExpectedStylish.txt"));
        expectedPlainResult = Files.readString(Path.
                of("src/test/resources/ExpectedPlain.txt"));
        expectedJSONResult = Files.readString(Path.
                of("src/test/resources/ExpectedJSON.txt"));
    }


    @Test
    void testDefaultOutPutFormat() throws Exception {
        String actualResult1 = Differ.generate(ymlTestFilePath1, ymlTestFilePath2);
        String actualResult2 = Differ.generate(jsonTestFilePath1, jsonTestFilePath2);

        assertThat(actualResult1).isEqualTo(expectedStylishResult);
        assertThat(actualResult2).isEqualTo(expectedStylishResult);
    }

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

    @Test
    void testGenerateJSONToJSONOutputFormat() throws Exception {
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2, JSON_FORMAT);
        assertThat(actualResult).isEqualTo(expectedJSONResult);
    }

    @Test
    void testGenerateYMLToJSONOutputFormat() throws Exception {
        String actualResult = Differ.generate(ymlTestFilePath1, ymlTestFilePath2, JSON_FORMAT);
        assertThat(actualResult).isEqualTo(expectedJSONResult);
    }


    @Test
    void testWithDifferentFormats1() throws Exception {
        String actualResult1 = Differ.generate(jsonTestFilePath1, ymlTestFilePath2, STYLISH);
        String actualResult2 = Differ.generate(ymlTestFilePath1, jsonTestFilePath2, STYLISH);

        assertThat(actualResult1).isEqualTo(expectedStylishResult);
        assertThat(actualResult2).isEqualTo(expectedStylishResult);
    }

    @Test
    void testWithDifferentFormats2() throws Exception {
        String actualResult1 = Differ.generate(jsonTestFilePath1, ymlTestFilePath2, PLAIN);
        String actualResult2 = Differ.generate(ymlTestFilePath1, jsonTestFilePath2, PLAIN);

        assertThat(actualResult1).isEqualTo(expectedPlainResult);
        assertThat(actualResult2).isEqualTo(expectedPlainResult);
    }

    @Test
    void testWithDifferentFormats3() throws Exception {
        String actualResult1 = Differ.generate(jsonTestFilePath1, ymlTestFilePath2);
        String actualResult2 = Differ.generate(ymlTestFilePath1, jsonTestFilePath2);

        assertThat(actualResult1).isEqualTo(expectedStylishResult);
        assertThat(actualResult2).isEqualTo(expectedStylishResult);
    }

    @Test
    void testWithDifferentFormats4() throws Exception {
        String actualResult1 = Differ.generate(jsonTestFilePath1, ymlTestFilePath2, JSON_FORMAT);
        String actualResult2 = Differ.generate(ymlTestFilePath1, jsonTestFilePath2, JSON_FORMAT);

        assertThat(actualResult1).isEqualTo(expectedJSONResult);
        assertThat(actualResult2).isEqualTo(expectedJSONResult);
    }
}
