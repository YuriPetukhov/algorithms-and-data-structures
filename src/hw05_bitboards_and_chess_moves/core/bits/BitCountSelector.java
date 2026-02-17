package hw05_bitboards_and_chess_moves.core.bits;

import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.common.props.Props;

import java.util.Locale;
import java.util.Properties;

public final class BitCountSelector {

    private static final String KEY = "hw05.popcount";
    private static final String RESOURCE = "hw05/application.properties";

    private BitCountSelector() {}

    public static BitCountStrategy fromConfig() {
        String v = System.getProperty(KEY);
        if (v != null && !v.trim().isEmpty()) {
            return fromId(v);
        }

        try {
            Properties p = new PropertiesLoader().loadFromClasspath(RESOURCE);
            String fromFile = Props.get(p, KEY, "jdk");
            return fromId(fromFile);
        } catch (Exception e) {
            return fromId("jdk");
        }
    }

    public static BitCountStrategy fromId(String id) {
        String k = (id == null ? "jdk" : id.trim().toLowerCase(Locale.ROOT));
        return switch (k) {
            case "jdk" -> new JdkBitCount();
            case "shift" -> new ShiftBitCount();
            case "kernighan" -> new KernighanBitCount();
            case "byte_cache", "cache", "bytecache" -> new ByteCacheBitCount();
            default -> throw new IllegalArgumentException("Unknown popcount: " + id +
                    " (use: jdk|shift|kernighan|byte_cache)");
        };
    }
}
