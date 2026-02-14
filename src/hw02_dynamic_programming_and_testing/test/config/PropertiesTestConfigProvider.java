package hw02_dynamic_programming_and_testing.test.config;

import static hw02_dynamic_programming_and_testing.common.props.Props.*;
import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.common.time.TimeUnitParser;

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
        String raw = p.getProperty("test.time.unit");

        TimeUnit timeUnit = TimeUnitParser.tryParse(raw)
                .orElse(TimeUnit.MILLISECONDS);
        int runs = Integer.parseInt(p.getProperty("test.benchmark.runs", "1"));


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
                timeUnit,
                runs
        );
    }
}