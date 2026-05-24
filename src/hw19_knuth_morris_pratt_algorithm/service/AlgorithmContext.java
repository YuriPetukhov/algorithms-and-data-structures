package hw19_knuth_morris_pratt_algorithm.service;

public interface AlgorithmContext<R> {
    R result();

    void setResult(R result);
}