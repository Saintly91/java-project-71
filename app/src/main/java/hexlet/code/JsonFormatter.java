package hexlet.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonFormatter {

    static class Item {
        public String key;
        public String status;
        public Object oldValue;
        public Object newValue;

        Item(String key, Status status, Object oldValue, Object newValue) {
            this.key = key;
            this.status = status.name().toLowerCase();
            this.oldValue = oldValue;
            this.newValue = newValue;
        }
    }

    public static String format(List<DiffEntry> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        List<Item> items = new ArrayList<>();
        for (DiffEntry e : diff) {
            items.add(new Item(e.getKey(), e.getStatus(), e.getOldValue(), e.getNewValue()));
        }

        return mapper.writeValueAsString(items);
    }
}
