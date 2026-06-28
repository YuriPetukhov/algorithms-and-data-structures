package hw21_dynamic_programming.test.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class ServiceLoaderFileTaskAdapterLoader
        implements FileTaskAdapterLoader {

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<FileTaskAdapter<?, ?>> load() {
        List<FileTaskAdapter<?, ?>> adapters = new ArrayList<>();
        ServiceLoader<FileTaskAdapter> loader = ServiceLoader.load(FileTaskAdapter.class);
        for (FileTaskAdapter adapter : loader) {
            adapters.add((FileTaskAdapter<?, ?>) adapter);
        }
        return List.copyOf(adapters);
    }
}
