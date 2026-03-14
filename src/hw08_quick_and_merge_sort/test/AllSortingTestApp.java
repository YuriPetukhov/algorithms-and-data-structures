package hw08_quick_and_merge_sort.test;

import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.test.config.ArgsOverrideProvider;
import hw02_dynamic_programming_and_testing.test.config.DefaultTestConfigProvider;
import hw02_dynamic_programming_and_testing.test.config.PropertiesTestConfigProvider;
import hw02_dynamic_programming_and_testing.test.config.TestConfig;
import hw02_dynamic_programming_and_testing.test.config.TestConfigProvider;
import hw02_dynamic_programming_and_testing.test.engine.DefaultTestEngineProvider;
import hw02_dynamic_programming_and_testing.test.engine.TestEngine;
import hw02_dynamic_programming_and_testing.test.engine.TestRunResult;
import hw06_sorting_algorithms.common.module.ModuleProvider;
import hw06_sorting_algorithms.common.module.ModuleRegistry;
import hw08_quick_and_merge_sort.test.report.FileTestReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class AllSortingTestApp {

    public static void main(String[] args) {
        try {
            List<Path> testDirs;
            try (Stream<Path> stream = Files.list(Path.of("tests/hw06"))) {
                testDirs = stream
                        .filter(Files::isDirectory)
                        .sorted()
                        .toList();
            }

            String ts = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            try (FileTestReporter reporter = new FileTestReporter(
                    Path.of("tests/results/all_sorting_test_" + ts + ".txt")
            )) {

                ModuleRegistry moduleRegistry = new ModuleRegistry();

                ModuleProvider sortingProvider = moduleRegistry.find("programs")
                        .orElseThrow();

                for (Path testDir : testDirs) {
                    for (ModuleProvider.TaskVariant variant : sortingProvider.variants()) {

                        System.out.println("[TEST] " + variant.name() + " | " + testDir.getFileName());

                        reporter.printSection(
                                testDir.getFileName().toString(),
                                variant.id(),
                                variant.name()
                        );

                        TestConfigProvider configProvider = new DefaultTestConfigProvider(
                                new PropertiesTestConfigProvider(
                                        new PropertiesLoader(),
                                        "hw08/application.properties"
                                ),
                                new ArgsOverrideProvider()
                        );

                        String[] overrideArgs = {
                                "--type", "programs",
                                "--task", variant.id(),
                                "--dir", testDir.toString()
                        };

                        TestConfig cfg = configProvider.provide(overrideArgs);

                        TestEngine engine = new DefaultTestEngineProvider().get();

                        TestRunResult run = engine.run(cfg, cfg.benchmarkRuns());

                        reporter.print(run.results());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}