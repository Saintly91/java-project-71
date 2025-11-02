package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public final class Parser {

    private Parser() {
    }

    public static Map<String, Object> parse(String content, String format) {
        try {
            ObjectMapper mapper;

            if ("json".equals(format)) {
                mapper = new ObjectMapper();
            } else if ("yml".equals(format) || "yaml".equals(format)) {
                mapper = new ObjectMapper(new YAMLFactory());
            } else {
                throw new RuntimeException("Unknown format: " + format);
            }

            return mapper.readValue(content, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Parsing error: " + e.getMessage(), e);
        }
    }
}
