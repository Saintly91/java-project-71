package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        String content1 = Files.readString(Path.of(filePath1));
        String content2 = Files.readString(Path.of(filePath2));

        String inputFormat1 = detectFormat(filePath1);
        String inputFormat2 = detectFormat(filePath2);

        Map<String, Object> data1 = Parser.parse(content1, inputFormat1);
        Map<String, Object> data2 = Parser.parse(content2, inputFormat2);

        List<DiffEntry> diff = DiffBuilder.build(data1, data2);

        return Formatter.format(diff, format);
    }

    private static String detectFormat(String filePath) {
        String lower = filePath.toLowerCase();
        if (lower.endsWith("json")) {
            return "json";
        } else if (lower.endsWith("yml")) {
            return "yml";
        } else {
            throw new RuntimeException("Unsupported input format: " + filePath);
        }
    }
}
