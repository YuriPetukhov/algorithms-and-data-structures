package hw17_dijkstra_algorithm.service.steps;

public interface Step<C> {
    void execute(C context);
}