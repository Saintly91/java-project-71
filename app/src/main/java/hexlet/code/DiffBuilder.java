package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {

    public static List<DiffEntry> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffEntry> diffEntries = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data1.containsKey(key)) {
                diffEntries.add(new DiffEntry(key, null, value2, Status.ADDED));
            } else if (!data2.containsKey(key)) {
                diffEntries.add(new DiffEntry(key, value1, null, Status.REMOVED));
            } else if (Objects.equals(value1, value2)) {
                diffEntries.add(new DiffEntry(key, value1, value2, Status.UNCHANGED));
            } else {
                diffEntries.add(new DiffEntry(key, value1, value2, Status.CHANGED));
            }
        }
        return diffEntries;
    }
}
