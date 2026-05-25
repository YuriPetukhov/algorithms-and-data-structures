package hw20_rle_file_compression.demo;

import hw20_rle_file_compression.libs.compression.FileProcessingResult;
import hw20_rle_file_compression.libs.compression.ImprovedRleCompressor;
import hw20_rle_file_compression.service.AlgorithmService;
import hw20_rle_file_compression.service.CompressionContext;
import hw20_rle_file_compression.service.Handler;
import hw20_rle_file_compression.service.safety.CompressionSafetyPolicy;
import hw20_rle_file_compression.service.steps.DecompressStep;
import hw20_rle_file_compression.service.steps.ReadFileStep;
import hw20_rle_file_compression.service.steps.ValidateCompressionInputStep;
import hw20_rle_file_compression.service.steps.ValidateFileSizeStep;
import hw20_rle_file_compression.service.steps.ValidateOutputSizeStep;
import hw20_rle_file_compression.service.steps.WriteFileStep;
import hw20_rle_file_compression.visualization.BenchmarkConsoleVisualizer;

import java.nio.file.Path;
import java.util.List;

public class RleDecompressionDemo {
    public static void main(String[] args) {
        CompressionContext<FileProcessingResult> context =
                new CompressionContext<>(
                        Path.of("src/hw20_rle_file_compression/demo/files/sample.irle"),
                        Path.of("src/hw20_rle_file_compression/demo/files/sample_restored.txt"),
                        new ImprovedRleCompressor(),
                        false
                );

        CompressionSafetyPolicy policy = new CompressionSafetyPolicy(
                10 * 1024 * 1024,
                100 * 1024 * 1024
        );

        AlgorithmService<CompressionContext<FileProcessingResult>, FileProcessingResult> service =
                new AlgorithmService<>(
                        new Handler<>(
                                List.of(
                                        new ValidateCompressionInputStep<>(),
                                        new ReadFileStep<>(),
                                        new ValidateFileSizeStep<>(policy),
                                        new DecompressStep(),
                                        new ValidateOutputSizeStep<>(policy),
                                        new WriteFileStep()
                                )
                        )
                );

        FileProcessingResult result = service.execute(context);

        BenchmarkConsoleVisualizer.title("RLE decompression demo");
        BenchmarkConsoleVisualizer.processingResult(result);
    }
}