package hw02_dynamic_programming_and_testing.report.transform;

import java.util.LinkedHashMap;
import java.util.Map;

public class RowKeyFormatRegistry {

    private final Map<String, RowKeyFormat> map = new LinkedHashMap<>();

    public RowKeyFormatRegistry() {
        java.util.ServiceLoader<RowKeyFormat> loader = java.util.ServiceLoader.load(RowKeyFormat.class);
        for (RowKeyFormat f : loader) {
            String id = f.id().trim().toLowerCase(java.util.Locale.ROOT);
            if (map.containsKey(id)) throw new IllegalStateException("Duplicate rowkey format id: " + id);
            map.put(id, f);
        }
    }

    public RowKeyFormat get(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Missing rowkey format id");
        RowKeyFormat f = map.get(id.trim().toLowerCase(java.util.Locale.ROOT));
        if (f == null) throw new IllegalArgumentException("Unknown rowkey format id: " + id);
        return f;
    }
}

