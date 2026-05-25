package hw20_rle_file_compression.libs.compression;

public record FileProcessingResult(
        String algorithm,
        String operation,
        int inputSize,
        int outputSize,
        double ratio
) {
    public static FileProcessingResult of(
            String algorithm,
            String operation,
            int inputSize,
            int outputSize
    ) {
        double ratio = inputSize == 0
                ? 0
                : (double) outputSize / inputSize;

        return new FileProcessingResult(
                algorithm,
                operation,
                inputSize,
                outputSize,
                ratio
        );
    }
}