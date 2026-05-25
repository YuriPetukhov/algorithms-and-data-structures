package hw20_rle_file_compression.service;

public interface AlgorithmContext<R> {
    R result();

    void setResult(R result);
}