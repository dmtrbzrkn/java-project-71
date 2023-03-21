package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.StatusChange;

import java.util.Map;

public class JSON {
    public static String format(Map<String, StatusChange> differences) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(differences);
    }
}
