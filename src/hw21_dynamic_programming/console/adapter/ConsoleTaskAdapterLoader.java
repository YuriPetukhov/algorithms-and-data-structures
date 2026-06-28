package hw21_dynamic_programming.console.adapter;

import java.util.List;

@FunctionalInterface
public interface ConsoleTaskAdapterLoader {

    List<ConsoleTaskAdapter<?, ?>> load();
}
