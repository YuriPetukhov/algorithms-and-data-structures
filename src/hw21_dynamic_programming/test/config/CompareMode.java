package hw21_dynamic_programming.test.config;

public enum CompareMode {
    EXACT,
    TRIM;

    public static CompareMode parse(String value) {
        if (value == null || value.isBlank()) {
            return TRIM;
        }
        return switch (value.trim().toLowerCase()) {
            case "exact" -> EXACT;
            case "trim" -> TRIM;
            default -> throw new IllegalArgumentException(
                    "Unknown compare mode: " + value + ". Expected exact or trim."
            );
        };
    }
}
