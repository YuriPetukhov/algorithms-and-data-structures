package hw21_dynamic_programming.tasks.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class ServiceLoaderTaskModuleLoader implements TaskModuleLoader {

    @Override
    public List<TaskModule> load() {
        List<TaskModule> modules = new ArrayList<>();
        ServiceLoader.load(TaskModule.class).forEach(modules::add);

        if (modules.isEmpty()) {
            throw new IllegalStateException(
                    "No task modules were found. Check META-INF/services registration."
            );
        }
        return List.copyOf(modules);
    }
}
