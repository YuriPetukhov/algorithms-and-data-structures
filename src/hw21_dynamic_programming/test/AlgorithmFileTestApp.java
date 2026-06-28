package hw21_dynamic_programming.test;

import hw21_dynamic_programming.test.adapter.FileTaskAdapterLoader;
import hw21_dynamic_programming.test.adapter.FileTaskAdapterRegistry;
import hw21_dynamic_programming.test.adapter.InMemoryFileTaskAdapterRegistry;
import hw21_dynamic_programming.test.adapter.ServiceLoaderFileTaskAdapterLoader;
import hw21_dynamic_programming.test.config.FileTestConfig;
import hw21_dynamic_programming.test.config.FileTestConfigParser;
import hw21_dynamic_programming.test.engine.FileTestEngine;
import hw21_dynamic_programming.test.engine.TestRunResult;
import hw21_dynamic_programming.test.report.ConsoleTestReporter;
import hw21_dynamic_programming.service.bootstrap.TaskServiceFactory;
import hw21_dynamic_programming.service.bootstrap.TaskServiceRuntime;

public final class AlgorithmFileTestApp {

    private AlgorithmFileTestApp() {
    }

    public static void main(String[] args) {
        FileTestConfigParser configParser = new FileTestConfigParser();
        if (configParser.helpRequested(args)) {
            System.out.println(configParser.usage());
            return;
        }

        ConsoleTestReporter reporter = new ConsoleTestReporter();
        try {
            FileTestConfig config = configParser.parse(args);
            TaskServiceRuntime service = TaskServiceFactory
                    .usingServiceLoader()
                    .create();

            FileTaskAdapterLoader adapterLoader =
                    new ServiceLoaderFileTaskAdapterLoader();
            FileTaskAdapterRegistry adapterRegistry =
                    new InMemoryFileTaskAdapterRegistry(
                            adapterLoader.load(),
                            service.taskRegistry()
                    );

            FileTestEngine engine = new FileTestEngine(
                    adapterRegistry,
                    service.executionHandler()
            );
            TestRunResult result = engine.run(config);
            reporter.print(result);
            if (!result.successful()) {
                System.exit(1);
            }
        } catch (Throwable error) {
            reporter.printFatal(error);
            System.err.println(configParser.usage());
            System.exit(2);
        }
    }
}
