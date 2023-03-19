package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JSON {
    public static String format(List<Map<String, Object>> differences) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(differences);
    }
}
