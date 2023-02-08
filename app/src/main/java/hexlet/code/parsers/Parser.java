package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {
    public static Map<String, String> parse(String fileContents) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileContents, new TypeReference<>() {
        });
    }
}
