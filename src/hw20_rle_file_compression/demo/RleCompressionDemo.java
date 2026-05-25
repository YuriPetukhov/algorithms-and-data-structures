package hw20_rle_file_compression.demo;

import hw20_rle_file_compression.libs.compression.FileProcessingResult;
import hw20_rle_file_compression.libs.compression.ImprovedRleCompressor;
import hw20_rle_file_compression.service.AlgorithmService;
import hw20_rle_file_compression.service.CompressionContext;
import hw20_rle_file_compression.service.Handler;
import hw20_rle_file_compression.service.safety.CompressionSafetyPolicy;
import hw20_rle_file_compression.service.steps.*;
import hw20_rle_file_compression.visualization.BenchmarkConsoleVisualizer;

import java.nio.file.Path;
import java.util.List;

public class RleCompressionDemo {
    public static void main(String[] args) {
        CompressionContext<FileProcessingResult> context =
                new CompressionContext<>(
                        Path.of("src/hw20_rle_file_compression/demo/files/sample.txt"),
                        Path.of("src/hw20_rle_file_compression/demo/files/sample.irle"),
                        new ImprovedRleCompressor(),
                        true
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
                                        new CompressStep(),
                                        new ValidateOutputSizeStep<>(policy),
                                        new WriteFileStep()
                                )
                        )
                );

        FileProcessingResult result = service.execute(context);

        BenchmarkConsoleVisualizer.title("RLE compression demo");
        BenchmarkConsoleVisualizer.processingResult(result);
    }
}