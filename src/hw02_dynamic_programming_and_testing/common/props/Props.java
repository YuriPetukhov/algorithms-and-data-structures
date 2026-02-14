package hw02_dynamic_programming_and_testing.common.props;

import java.util.Properties;

public final class Props {

    private Props() {}

    public static String get(Properties p, String key, String def) {
        String v = p.getProperty(key);
        if (v == null) return def;
        v = v.trim();
        return v.isEmpty() ? def : v;
    }

    public static boolean getBool(Properties p, String key, boolean def) {
        String v = p.getProperty(key);
        if (v == null) return def;

        v = v.trim().toLowerCase();
        if (v.isEmpty()) return def;

        return v.equals("true")
                || v.equals("1")
                || v.equals("yes")
                || v.equals("y");
    }

    public static int getInt(Properties p, String key, int def) {
        String v = p.getProperty(key);
        if (v == null) return def;

        v = v.trim();
        if (v.isEmpty()) return def;

        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid integer for key '" + key + "': " + v
            );
        }
    }
}
