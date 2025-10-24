package hexlet.code;

import java.util.List;

public class Formatter {

    public static String format(List<DiffEntry> diff, String formatName) throws Exception {
        String normalized = (formatName == null || formatName.isBlank())
                ? "stylish"
                : formatName.toLowerCase();

        return switch (normalized) {
            case "plain" -> PlainFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + normalized);
        };
    }
}
