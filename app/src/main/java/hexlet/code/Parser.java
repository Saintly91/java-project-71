package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));

        ObjectMapper mapper;
        if (filePath.endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            mapper = new ObjectMapper(new YAMLFactory()); // YAML
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }

        return mapper.readValue(content, new TypeReference<>() {});
    }
}
