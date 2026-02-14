package hw02_dynamic_programming_and_testing.test;

import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.test.config.*;
import hw02_dynamic_programming_and_testing.test.engine.DefaultTestEngineProvider;
import hw02_dynamic_programming_and_testing.test.engine.TestEngine;
import hw02_dynamic_programming_and_testing.test.engine.TestRunResult;
import hw02_dynamic_programming_and_testing.test.report.ConsoleTestReporter;

public class AlgorithmTestApp {

    public static void main(String[] args) {
        ConsoleTestReporter reporter = new ConsoleTestReporter();

        try {
            TestConfigProvider configProvider = new DefaultTestConfigProvider(
                    new PropertiesTestConfigProvider(new PropertiesLoader(), "hw02/application.properties"),
                    new ArgsOverrideProvider()
            );

            TestConfig cfg = configProvider.provide(args);

            TestEngine engine = new DefaultTestEngineProvider().get();
            TestRunResult run = engine.run(cfg, cfg.benchmarkRuns());

            reporter.print(run.results());

        } catch (Exception e) {
            reporter.printFatal(e);
        }
    }
}
