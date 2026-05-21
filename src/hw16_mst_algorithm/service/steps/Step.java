package hw16_mst_algorithm.service.steps;

public interface Step<C> {
    void execute(C context);
}