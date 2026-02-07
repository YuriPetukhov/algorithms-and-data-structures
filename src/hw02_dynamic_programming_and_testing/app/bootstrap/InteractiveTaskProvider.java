package hw02_dynamic_programming_and_testing.app.bootstrap;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.app.registry.TaskRegistry;
import hw02_dynamic_programming_and_testing.app.util.ConsoleIO;

import java.util.List;

public class InteractiveTaskProvider implements TaskProvider {

    private final TaskRegistry registry;
    private final ConsoleIO io;

    public InteractiveTaskProvider(TaskRegistry registry, ConsoleIO io) {
        if (registry == null) throw new IllegalArgumentException("registry must not be null");
        if (io == null) throw new IllegalArgumentException("io must not be null");
        this.registry = registry;
        this.io = io;
    }

    @Override
    public SelectedTask provide(String[] args) throws Exception {
        List<Task> tasks = registry.list();
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No tasks registered");
        }

        io.println("Выберите задачу:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            io.println("  " + (i + 1) + ") " + t.displayName() + " [" + t.id() + "]");
        }

        int choice = readChoice(tasks.size());
        Task selected = tasks.get(choice - 1);

        io.print("Введите входные данные (одна строка): ");
        String input = io.readLine();
        if (input == null) input = "";
        input = input.trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }

        return new SelectedTask(selected, input);
    }

    private int readChoice(int max) throws Exception {
        while (true) {
            io.print("Номер: ");
            String s = io.readLine();
            if (s == null) s = "";
            s = s.trim();

            try {
                int n = Integer.parseInt(s);
                if (n >= 1 && n <= max) return n;
            } catch (NumberFormatException ignored) { }

            io.println("Некорректный номер. Введите число от 1 до " + max + ".");
        }
    }
}
