package hw17_dijkstra_algorithm.service;

public interface AlgorithmContext<R> {
    R result();

    void setResult(R result);
}