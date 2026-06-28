package hw21_dynamic_programming.workflow;

public interface ExecutionStep {

    String id();

    void execute(ExecutionContext context);
}
