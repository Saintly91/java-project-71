package hexlet.code;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<DiffEntry> diff) {
        StringBuilder sb = new StringBuilder();

        for (DiffEntry e : diff) {
            String key = e.getKey();
            Status st = e.getStatus();
            Object oldV = e.getOldValue();
            Object newV = e.getNewValue();

            switch (st) {
                case UNCHANGED -> {
                }
                case REMOVED -> sb.append("Property '")
                        .append(key)
                        .append("' was removed")
                        .append("\n");
                case ADDED -> sb.append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(stringify(newV))
                        .append("\n");
                case CHANGED -> sb.append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(stringify(oldV))
                        .append(" to ")
                        .append(stringify(newV))
                        .append("\n");
                default -> {
                }
            }
        }

        return sb.toString().trim();
    }

    private static String stringify(Object v) {
        if (v == null) {
            return "null";
        }
        if (v instanceof String s) {
            return "'" + s + "'";
        }
        if (isComplex(v)) {
            return "[complex value]";
        }
        return v.toString();
    }

    private static boolean isComplex(Object v) {
        if (v == null) {
            return false;
        }
        if (v instanceof Map<?, ?>) {
            return true;
        }
        if (v instanceof Iterable<?>) {
            return true;
        }
        return v.getClass().isArray();
    }
}
