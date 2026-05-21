package hw16_mst_algorithm.service;

public interface AlgorithmContext<R> {
    R result();

    void setResult(R result);
}