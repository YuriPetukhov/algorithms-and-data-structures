package hw02_dynamic_programming_and_testing.test.config;

public enum CompareMode {
    NONE,
    TRIM;

    public static CompareMode from(String s) {
        if (s == null) return TRIM;
        String v = s.trim().toLowerCase();
        return switch (v) {
            case "none" -> NONE;
            case "trim" -> TRIM;
            default -> TRIM;
        };
    }
}
