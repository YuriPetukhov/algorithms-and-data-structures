package hw08_quick_and_merge_sort.report;

import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.report.config.ReportConfig;
import hw02_dynamic_programming_and_testing.report.io.TableConsolePrinter;
import hw02_dynamic_programming_and_testing.report.model.ReportTable;
import hw02_dynamic_programming_and_testing.report.service.ReportRunner;
import hw02_dynamic_programming_and_testing.test.config.*;
import hw02_dynamic_programming_and_testing.test.engine.DefaultTestEngineProvider;
import hw02_dynamic_programming_and_testing.test.engine.TestEngine;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public final class Hw08ReportApp {

    public static void main(String[] args) {
        try {
            PropertiesLoader propertiesLoader = new PropertiesLoader();

            var reportProperties =
                    propertiesLoader.loadFromClasspath("hw08/report.properties");
            ReportConfig reportConfig =
                    ReportConfig.fromProperties(reportProperties);

            TestEngine engine = new DefaultTestEngineProvider().get();
            TableConsolePrinter printer = new TableConsolePrinter();

            String ts = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            Files.createDirectories(Path.of("tests/results"));
            System.setOut(new PrintStream(
                    "tests/results/sorting_report_" + ts + ".txt"
            ));

            List<Path> testDirs;
            try (Stream<Path> stream = Files.list(Path.of("tests/hw06"))) {
                testDirs = stream
                        .filter(Files::isDirectory)
                        .sorted()
                        .toList();
            }

            for (Path testDir : testDirs) {

                System.out.println();
                System.out.println("========================================");
                System.out.println("REPORT FOR: " + testDir);
                System.out.println("========================================");

                TestConfigProvider configProvider =
                        new DefaultTestConfigProvider(
                                new PropertiesTestConfigProvider(
                                        propertiesLoader,
                                        "hw08/application.properties"
                                ),
                                new ArgsOverrideProvider()
                        );

                String[] overrideArgs = {
                        "--dir", testDir.toString()
                };

                ReportRunner reportRunner =
                        new ReportRunner(engine, extraArgs -> {
                            String[] merged = new String[overrideArgs.length + extraArgs.length];

                            System.arraycopy(overrideArgs, 0, merged, 0, overrideArgs.length);
                            System.arraycopy(extraArgs, 0, merged, overrideArgs.length, extraArgs.length);

                            return configProvider.provide(merged);
                        });

                for (ReportConfig.Suite suite : reportConfig.suites()) {

                    ReportTable table =
                            reportRunner.runByInputTable(suite);

                    printer.print(table);
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}