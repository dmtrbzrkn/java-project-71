package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.parsers.ParserFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static hexlet.code.Tree.genDiff;
import static hexlet.code.formatters.Formatter.STYLISH;

public class Differ {

    public static String generate(String filePath1, String filePath2, String outPutFormat) throws Exception {
        Map<String, Object> firstFileData = getData(filePath1);
        Map<String, Object> secondFileData = getData(filePath2);
        Map<String, StatusChange> diff = genDiff(firstFileData, secondFileData);
        return Formatter.format(diff, outPutFormat);
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
        var parser = ParserFactory.getParser(getFileExtension(String.valueOf(path)));
        return parser.parse(contents);
    }

    private static String getFileExtension(String pathToFile) {
        return pathToFile.substring(pathToFile.indexOf(".") + 1);
    }

    private static Path getAbsolutePath(String filePath) {
        return Path.of(filePath).toAbsolutePath().normalize();
    }
}
