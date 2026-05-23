package hw18_boyer_moore_algorithm.service;

public interface AlgorithmContext<R> {
    R result();

    void setResult(R result);
}