package hw20_rle_file_compression.service;

import hw20_rle_file_compression.libs.compression.Compressor;
import hw20_rle_file_compression.libs.compression.FileProcessingResult;

import java.nio.file.Path;

public class CompressionContext<R> implements AlgorithmContext<R> {
    private final Path inputPath;
    private final Path outputPath;
    private final Compressor compressor;
    private final boolean compress;

    private byte[] inputData;
    private byte[] outputData;

    private FileProcessingResult processingResult;
    private R result;

    public CompressionContext(
            Path inputPath,
            Path outputPath,
            Compressor compressor,
            boolean compress
    ) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.compressor = compressor;
        this.compress = compress;
    }

    public Path inputPath() {
        return inputPath;
    }

    public Path outputPath() {
        return outputPath;
    }

    public Compressor compressor() {
        return compressor;
    }

    public boolean compress() {
        return compress;
    }

    public byte[] inputData() {
        return inputData;
    }

    public void setInputData(byte[] inputData) {
        this.inputData = inputData;
    }

    public byte[] outputData() {
        return outputData;
    }

    public void setOutputData(byte[] outputData) {
        this.outputData = outputData;
    }

    public FileProcessingResult processingResult() {
        return processingResult;
    }

    public void setProcessingResult(FileProcessingResult processingResult) {
        this.processingResult = processingResult;
    }

    @Override
    public R result() {
        return result;
    }

    @Override
    public void setResult(R result) {
        this.result = result;
    }
}