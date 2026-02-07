package hw02_dynamic_programming_and_testing.test.config;

import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class PropertiesTestConfigProvider implements TestConfigProvider {

    private final PropertiesLoader loader;
    private final String resourceName;

    public PropertiesTestConfigProvider(PropertiesLoader loader, String resourceName) {
        this.loader = loader;
        this.resourceName = resourceName;
    }

    @Override
    public TestConfig provide(String[] args) throws Exception {
        Properties p = loader.loadFromClasspath(resourceName);

        String taskId = get(p, "test.task.id", null);
        Path testDir = Path.of(get(p, "test.dir", "tests/hw02"));

        String inputExt = get(p, "test.input.ext", ".in");
        String outputExt = get(p, "test.output.ext", ".out");

        CompareMode compareMode = CompareMode.from(get(p, "test.compare", "trim"));

        boolean showPassed = getBool(p, "test.show.passed", true);
        boolean showFailed = getBool(p, "test.show.failed", true);
        boolean showDiff = getBool(p, "test.show.diff", true);

        boolean timeEnabled = getBool(p, "test.time.enabled", true);
        TimeUnit timeUnit = parseTimeUnit(get(p, "test.time.unit", "millis"));

        return new TestConfig(
                taskId,
                testDir,
                inputExt,
                outputExt,
                compareMode,
                showPassed,
                showFailed,
                showDiff,
                timeEnabled,
                timeUnit
        );
    }

    private static String get(Properties p, String key, String def) {
        String v = p.getProperty(key);
        if (v == null) return def;
        v = v.trim();
        return v.isEmpty() ? def : v;
    }

    private static boolean getBool(Properties p, String key, boolean def) {
        String v = p.getProperty(key);
        if (v == null) return def;
        v = v.trim().toLowerCase();
        if (v.isEmpty()) return def;
        return v.equals("true") || v.equals("1") || v.equals("yes") || v.equals("y");
    }

    private static TimeUnit parseTimeUnit(String s) {
        if (s == null) return TimeUnit.MILLISECONDS;
        String v = s.trim().toLowerCase();
        return switch (v) {
            case "nanos", "nano", "ns" -> TimeUnit.NANOSECONDS;
            case "micros", "micro", "us" -> TimeUnit.MICROSECONDS;
            case "millis", "milli", "ms" -> TimeUnit.MILLISECONDS;
            case "seconds", "second", "sec", "s" -> TimeUnit.SECONDS;
            default -> TimeUnit.MILLISECONDS;
        };
    }
}