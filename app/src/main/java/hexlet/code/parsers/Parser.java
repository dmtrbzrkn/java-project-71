package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {
    public static Map parse(String fileContents) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.<Map<String, Object>>readValue(fileContents, new TypeReference<>() {
        });
    }
}
