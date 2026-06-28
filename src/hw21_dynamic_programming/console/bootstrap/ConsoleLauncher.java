package hw21_dynamic_programming.console.bootstrap;

import hw21_dynamic_programming.console.ConsoleApplication;
import hw21_dynamic_programming.service.bootstrap.TaskServiceFactory;
import hw21_dynamic_programming.service.bootstrap.TaskServiceRuntime;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

public final class ConsoleLauncher {

    private final TaskServiceFactory serviceFactory;
    private final ConsoleApplicationFactory consoleFactory;

    public ConsoleLauncher(
            TaskServiceFactory serviceFactory,
            ConsoleApplicationFactory consoleFactory
    ) {
        this.serviceFactory = Objects.requireNonNull(
                serviceFactory,
                "Task service factory must not be null."
        );
        this.consoleFactory = Objects.requireNonNull(
                consoleFactory,
                "Console application factory must not be null."
        );
    }

    public static ConsoleLauncher usingServiceLoaders() {
        return new ConsoleLauncher(
                TaskServiceFactory.usingServiceLoader(),
                ConsoleApplicationFactory.usingServiceLoader()
        );
    }

    public void run(InputStream input, PrintStream output) {
        TaskServiceRuntime service = serviceFactory.create();
        ConsoleApplication console = consoleFactory.create(service, input, output);
        console.run();
    }
}
