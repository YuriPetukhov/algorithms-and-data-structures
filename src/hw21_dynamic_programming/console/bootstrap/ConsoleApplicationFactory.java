package hw21_dynamic_programming.console.bootstrap;

import hw21_dynamic_programming.console.ConsoleApplication;
import hw21_dynamic_programming.console.InputFormReader;
import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapterLoader;
import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapterRegistry;
import hw21_dynamic_programming.console.adapter.InMemoryConsoleTaskAdapterRegistry;
import hw21_dynamic_programming.console.adapter.ServiceLoaderConsoleTaskAdapterLoader;
import hw21_dynamic_programming.console.controller.ConsoleTaskController;
import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.io.PrintStreamConsoleOutput;
import hw21_dynamic_programming.console.io.ScannerConsoleInput;
import hw21_dynamic_programming.console.menu.StandardTaskMenu;
import hw21_dynamic_programming.console.menu.StandardTaskSelectionReader;
import hw21_dynamic_programming.console.presentation.StandardErrorPresenter;
import hw21_dynamic_programming.console.presentation.StandardResultPresenter;
import hw21_dynamic_programming.service.bootstrap.TaskServiceRuntime;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

public final class ConsoleApplicationFactory {

    private final ConsoleTaskAdapterLoader adapterLoader;

    public ConsoleApplicationFactory(ConsoleTaskAdapterLoader adapterLoader) {
        this.adapterLoader = Objects.requireNonNull(
                adapterLoader,
                "Console adapter loader must not be null."
        );
    }

    public static ConsoleApplicationFactory usingServiceLoader() {
        return new ConsoleApplicationFactory(
                new ServiceLoaderConsoleTaskAdapterLoader()
        );
    }

    public ConsoleApplication create(
            TaskServiceRuntime service,
            InputStream inputStream,
            PrintStream outputStream
    ) {
        Objects.requireNonNull(service, "Task service must not be null.");
        Objects.requireNonNull(inputStream, "Input stream must not be null.");
        Objects.requireNonNull(outputStream, "Output stream must not be null.");

        ConsoleInput input = new ScannerConsoleInput(inputStream);
        ConsoleOutput output = new PrintStreamConsoleOutput(outputStream);
        ConsoleTaskAdapterRegistry adapterRegistry =
                new InMemoryConsoleTaskAdapterRegistry(
                        adapterLoader.load(),
                        service.taskRegistry()
                );

        ConsoleTaskController taskController = new ConsoleTaskController(
                adapterRegistry,
                new InputFormReader(input, output),
                service.executionHandler(),
                new StandardResultPresenter(output)
        );

        return new ConsoleApplication(
                service.taskRegistry(),
                new StandardTaskMenu(output),
                new StandardTaskSelectionReader(input, output, "> "),
                taskController,
                new StandardErrorPresenter(output),
                output
        );
    }
}
