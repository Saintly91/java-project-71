package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        String content1 = Files.readString(Paths.get(filePath1));
        String content2 = Files.readString(Paths.get(filePath2));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data1 = mapper.readValue(content1, new TypeReference<>() {});
        Map<String, Object> data2 = mapper.readValue(content2, new TypeReference<>() {});

        System.out.println(data1);
        System.out.println(data2);
        return data1.toString() + data2.toString();
    }
}
