package hw21_dynamic_programming.test.adapter;

import java.util.List;

public interface FileTaskAdapterLoader {

    List<FileTaskAdapter<?, ?>> load();
}
