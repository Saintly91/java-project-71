package hexlet.code;

import java.util.List;

public class Formatter {
    public static String format(String formatName, List<DiffEntry> diff) throws Exception {
        String name = (formatName == null || formatName.isBlank())
                ? "stylish"
                : formatName.toLowerCase();

        return  switch (formatName) {
            case "plain" -> PlainFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            default -> throw new Exception();
        };
    }
}
