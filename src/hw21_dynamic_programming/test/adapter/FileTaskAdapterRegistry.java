package hw21_dynamic_programming.test.adapter;

public interface FileTaskAdapterRegistry {

    FileTaskAdapter<?, ?> getRequired(String taskId);
}
