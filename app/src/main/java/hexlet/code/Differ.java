package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data1 = mapper.readValue(Files.readString(Path.of(filePath1)), new TypeReference<>() { });
        Map<String, Object> data2 = mapper.readValue(Files.readString(Path.of(filePath2)), new TypeReference<>() { });

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else if (Objects.equals(value1, value2)) {
                result.append("    ").append(key).append(": ").append(value1).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
