package hw02_dynamic_programming_and_testing.app.bootstrap;

public interface TaskProvider {
    SelectedTask provide(String[] args) throws Exception;
}
