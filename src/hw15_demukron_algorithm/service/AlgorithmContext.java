package hw15_demukron_algorithm.service;

public interface AlgorithmContext<R> {
    R result();

    void setResult(R result);
}