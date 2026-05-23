package hw18_boyer_moore_algorithm.service.steps;

public interface Step<C> {
    void execute(C context);
}