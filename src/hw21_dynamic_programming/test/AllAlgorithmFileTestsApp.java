package hw21_dynamic_programming.test;

import hw21_dynamic_programming.test.adapter.*;
import hw21_dynamic_programming.test.config.*;
import hw21_dynamic_programming.test.engine.*;
import hw21_dynamic_programming.test.report.ConsoleTestReporter;
import hw21_dynamic_programming.service.bootstrap.*;

import java.util.List;

public final class AllAlgorithmFileTestsApp {

    private record Suite(String taskId, String directory) {
    }

    public static void main(String[] args) throws Exception {
        List<Suite> suites = List.of(
                new Suite("fraction-sum", "tests/hw21/fraction"),
                new Suite("christmas-tree-max-path", "tests/hw21/tree"),
                new Suite("five-eight-count", "tests/hw21/fiveeight"),
                new Suite("island-count", "tests/hw21/island"),
                new Suite("small-barn", "tests/hw21/smallbarn"),
                new Suite("barn-free-height", "tests/hw21/freeheight"),
                new Suite("barn-width-bounds", "tests/hw21/widthbounds"),
                new Suite("big-barn", "tests/hw21/bigbarn")
        );

        TaskServiceRuntime service = TaskServiceFactory
                .usingServiceLoader()
                .create();

        FileTaskAdapterRegistry adapterRegistry =
                new InMemoryFileTaskAdapterRegistry(
                        new ServiceLoaderFileTaskAdapterLoader().load(),
                        service.taskRegistry()
                );

        FileTestEngine engine = new FileTestEngine(
                adapterRegistry,
                service.executionHandler()
        );

        FileTestConfigParser parser = new FileTestConfigParser();
        ConsoleTestReporter reporter = new ConsoleTestReporter();

        boolean allSuccessful = true;

        for (Suite suite : suites) {
            FileTestConfig config = parser.parse(new String[]{
                    "--task", suite.taskId(),
                    "--dir", suite.directory()
            });

            TestRunResult result = engine.run(config);
            reporter.print(result);

            allSuccessful &= result.successful();
            System.out.println();
        }

        System.exit(allSuccessful ? 0 : 1);
    }
}