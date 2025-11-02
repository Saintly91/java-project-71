package hexlet.code;

import java.util.List;

public class StylishFormatter {
    public static String format(List<DiffEntry> diff) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        for (DiffEntry e : diff) {
            String key = e.getKey();
            Status st = e.getStatus();
            Object oldV = e.getOldValue();
            Object newV = e.getNewValue();

            switch (st) {
                case UNCHANGED -> sb.append("    ").append(key).append(": ").append(stringify(oldV)).append("\n");
                case REMOVED -> sb.append("  - ").append(key).append(": ").append(stringify(oldV)).append("\n");
                case ADDED -> sb.append("  + ").append(key).append(": ").append(stringify(newV)).append("\n");
                case CHANGED -> {
                    sb.append("  - ").append(key).append(": ").append(stringify(oldV)).append("\n");
                    sb.append("  + ").append(key).append(": ").append(stringify(newV)).append("\n");
                }
                default -> throw new IllegalStateException("Unexpected status: " + st);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String stringify(Object v) {
        return  v == null ? "null" : v.toString();
    }
}

