package hw19_knuth_morris_pratt_algorithm.service.steps;

public interface Step<C> {
    void execute(C context);
}