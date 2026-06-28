package hw21_dynamic_programming.console.adapter;

public interface ConsoleTaskAdapterRegistry {

    ConsoleTaskAdapter<?, ?> getRequired(String taskId);
}
