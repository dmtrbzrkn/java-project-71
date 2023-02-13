package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.lang.reflect.Type;
import java.util.Map;

public final class YMLParser implements Parser {
    @Override
    public Map<String, Object> parse(String content) throws Exception {
        ObjectMapper objectMapper = new YAMLMapper();
        return objectMapper.readValue(content, new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
    }
}
