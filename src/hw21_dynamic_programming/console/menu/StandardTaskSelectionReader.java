package hw21_dynamic_programming.console.menu;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.List;
import java.util.Objects;

public final class StandardTaskSelectionReader
        implements TaskSelectionReader {

    private final ConsoleInput input;
    private final ConsoleOutput output;
    private final String prompt;

    public StandardTaskSelectionReader(
            ConsoleInput input,
            ConsoleOutput output,
            String prompt
    ) {
        this.input = Objects.requireNonNull(input, "Console input must not be null.");
        this.output = Objects.requireNonNull(output, "Console output must not be null.");
        this.prompt = Objects.requireNonNull(prompt, "Selection prompt must not be null.");
    }

    @Override
    public TaskSelection read(List<TaskDefinition<?, ?>> tasks) {
        Objects.requireNonNull(tasks, "Tasks must not be null.");

        while (true) {
            output.print(prompt);
            String value = input.readLine().trim();

            if (value.equals("0") || value.equalsIgnoreCase("exit")) {
                return TaskSelection.exit();
            }

            try {
                int index = Integer.parseInt(value) - 1;
                if (index < 0 || index >= tasks.size()) {
                    output.println("Ошибка ввода: задачи с таким номером нет.");
                    continue;
                }
                return TaskSelection.execute(tasks.get(index).id());
            } catch (NumberFormatException exception) {
                output.println(
                        "Ошибка ввода: введите номер задачи или 0 для выхода."
                );
            }
        }
    }
}
