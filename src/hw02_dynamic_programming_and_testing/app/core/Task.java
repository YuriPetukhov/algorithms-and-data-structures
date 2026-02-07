package hw02_dynamic_programming_and_testing.app.core;

public interface Task {
    String id();
    String displayName();
    String run(String input) throws Exception;
}


