package hexlet.code.parsers;


public class ParserFactory {

    public static Parser getParser(String dataFormat) {
        return switch (dataFormat.toUpperCase()) {
            case "JSON" -> new JSONParser();
            case "YML", "YAML" -> new YMLParser();
            default -> throw new Error("No such format" + dataFormat);
        };
    }
}
