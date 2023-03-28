package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    private static final Integer ERROR_CODE = 0;
    private static final Integer SUCCESS_CODE = 1;
    @Parameters(index = "0", paramLabel = "filepath1",
            description = "path to first file")
    private String filePath1;
    @Parameters(index = "1", paramLabel = "filepath2",
            description = "path to second file")
    private String filePath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String formatName;

    public static void main(String[] args) {
        try {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        } catch (CommandLine.InitializationException | CommandLine.ParameterException exception) {
            System.err.println(exception.getMessage());
        }

    }

    @Override
    public final Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, formatName));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return ERROR_CODE;
        }
        return SUCCESS_CODE;
    }
}
