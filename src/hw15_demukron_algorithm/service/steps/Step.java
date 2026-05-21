package hw15_demukron_algorithm.service.steps;

public interface Step<C> {
    void execute(C context);
}