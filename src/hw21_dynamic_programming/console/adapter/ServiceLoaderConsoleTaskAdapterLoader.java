package hw21_dynamic_programming.console.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class ServiceLoaderConsoleTaskAdapterLoader
        implements ConsoleTaskAdapterLoader {

    @Override
    public List<ConsoleTaskAdapter<?, ?>> load() {
        List<ConsoleTaskAdapter<?, ?>> adapters = new ArrayList<>();
        ServiceLoader.load(ConsoleTaskAdapter.class).forEach(adapters::add);

        if (adapters.isEmpty()) {
            throw new IllegalStateException(
                    "No console task adapters were found. "
                            + "Check META-INF/services registration."
            );
        }
        return List.copyOf(adapters);
    }
}
