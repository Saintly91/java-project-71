package hexlet.code;

import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);

        List<DiffEntry> diff = DiffBuilder.build(data1, data2);

        if (format == null || format.isBlank() || format.equalsIgnoreCase("stylish")) {
            return StylishFormatter.format(diff);
        }
        throw new IllegalArgumentException("Unknown format: " + format);
    }
}
