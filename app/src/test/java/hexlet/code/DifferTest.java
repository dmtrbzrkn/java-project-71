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
            of("/home/hexlet/Documents/projects/java-project-71/app/src/test/resources/ExpectedStylish.txt"));

    @Test
    void testGenerateStylishOutputFormat() throws Exception {
        String jsonTestFilePath1 = "src/test/resources/TestJSON1.json";
        String jsonTestFilePath2 = "src/test/resources/TestJSON2.json";
        String actualResult = Differ.generate(jsonTestFilePath1, jsonTestFilePath2);
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }
}
