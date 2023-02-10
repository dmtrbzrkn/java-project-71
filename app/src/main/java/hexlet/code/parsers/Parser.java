package hexlet.code.parsers;

import java.util.Map;

public interface Parser {

    Map<String, String> parse(String content) throws Exception;

}
