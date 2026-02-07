package hw02_dynamic_programming_and_testing.app.bootstrap;

import hw02_dynamic_programming_and_testing.app.registry.TaskRegistry;
import hw02_dynamic_programming_and_testing.app.util.ConsoleIO;

public class DefaultTaskProvider implements TaskProvider {

    private final TaskRegistry registry;
    private final ConsoleIO io;

    public DefaultTaskProvider(TaskRegistry registry, ConsoleIO io) {
        this.registry = registry;
        this.io = io;
    }

    @Override
    public SelectedTask provide(String[] args) throws Exception {
        if (hasArgs(args)) {
            return new ArgsTaskProvider(registry).provide(args);
        }
        return new InteractiveTaskProvider(registry, io).provide(args);
    }

    private boolean hasArgs(String[] args) {
        if (args == null) return false;
        for (String a : args) {
            if (a != null && a.startsWith("--")) return true;
        }
        return false;
    }
}

