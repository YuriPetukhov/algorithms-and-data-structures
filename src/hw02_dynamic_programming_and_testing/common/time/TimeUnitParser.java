package hw02_dynamic_programming_and_testing.common.time;

public final class TimeUnitParser {
    private TimeUnitParser() {}

    public static java.util.Optional<java.util.concurrent.TimeUnit> tryParse(String s) {
        if (s == null) return java.util.Optional.empty();
        String v = s.trim().toLowerCase();
        if (v.isEmpty()) return java.util.Optional.empty();

        return java.util.Optional.of(switch (v) {
            case "nanos", "nano", "ns" -> java.util.concurrent.TimeUnit.NANOSECONDS;
            case "micros", "micro", "us" -> java.util.concurrent.TimeUnit.MICROSECONDS;
            case "millis", "milli", "ms" -> java.util.concurrent.TimeUnit.MILLISECONDS;
            case "seconds", "second", "sec", "s" -> java.util.concurrent.TimeUnit.SECONDS;
            case "minutes", "minute", "min", "m" -> java.util.concurrent.TimeUnit.MINUTES;
            case "hours", "hour", "h" -> java.util.concurrent.TimeUnit.HOURS;
            case "days", "day", "d" -> java.util.concurrent.TimeUnit.DAYS;
            default -> throw new IllegalArgumentException("Unknown time unit: " + s);
        });
    }
}

