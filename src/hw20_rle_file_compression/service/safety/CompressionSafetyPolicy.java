package hw20_rle_file_compression.service.safety;

public record CompressionSafetyPolicy(
        int maxInputSizeBytes,
        int maxOutputSizeBytes
) {
}