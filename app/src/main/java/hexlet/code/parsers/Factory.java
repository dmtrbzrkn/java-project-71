package hexlet.code.parsers;


public class Factory {

    public static Parser getParser(String dataFormat) throws Exception {
        return switch (dataFormat.toUpperCase()) {
            case "JSON" -> new JSONParser();
            case "YML", "YAML" -> new YMLParser();
            default -> throw new IllegalArgumentException("Wrong data format:" + dataFormat);
        };
    }
}
