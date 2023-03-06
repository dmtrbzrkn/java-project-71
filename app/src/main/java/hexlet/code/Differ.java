package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.parsers.Factory;
import hexlet.code.Tree.Status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static hexlet.code.Tree.genDiff;
import static hexlet.code.formatters.Format.STYLISH;

public class Differ {

    public static String generate(String filePath1, String filePath2, String outPutFormat) throws Exception {
        Map<String, Object> originalMap = getData(filePath1);
        Map<String, Object> comparedMap = getData(filePath2);
        Map<String, Status> differences = genDiff(originalMap, comparedMap);
        return Format.formatSelection(originalMap, comparedMap, differences, outPutFormat);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, STYLISH);
    }

    private static Map<String, Object> getData(String filePath) throws Exception {
        Path path = getAbsolutePath(filePath);
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

    private static Path getAbsolutePath(String filePath) {
        return Path.of(filePath).toAbsolutePath().normalize();
    }
}
