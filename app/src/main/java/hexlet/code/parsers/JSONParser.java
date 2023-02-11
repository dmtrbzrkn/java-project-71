package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JSONParser implements Parser {
    @Override
    public Map<String, Object> parse(String fileContents) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileContents, new TypeReference<>() {
        });
    }
}
