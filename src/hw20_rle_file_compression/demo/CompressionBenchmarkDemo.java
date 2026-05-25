package hw20_rle_file_compression.demo;

import hw20_rle_file_compression.benchmark.BenchmarkCase;
import hw20_rle_file_compression.benchmark.BenchmarkResult;
import hw20_rle_file_compression.benchmark.CompressionBenchmark;
import hw20_rle_file_compression.libs.compression.Compressor;
import hw20_rle_file_compression.libs.compression.ImprovedRleCompressor;
import hw20_rle_file_compression.libs.compression.RleCompressor;
import hw20_rle_file_compression.visualization.BenchmarkConsoleVisualizer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CompressionBenchmarkDemo {
    public static void main(String[] args) {
        List<Compressor> compressors = List.of(
                new RleCompressor(),
                new ImprovedRleCompressor()
        );

        List<BenchmarkCase> cases = List.of(
                new BenchmarkCase(
                        "Text",
                        Path.of("src/hw20_rle_file_compression/demo/files/text.txt")
                ),
                new BenchmarkCase(
                        "Photo",
                        Path.of("src/hw20_rle_file_compression/demo/files/photo.jpg")
                ),
                new BenchmarkCase(
                        "Audio",
                        Path.of("src/hw20_rle_file_compression/demo/files/audio.mp3")
                ),
                new BenchmarkCase(
                        "Zip",
                        Path.of("src/hw20_rle_file_compression/demo/files/archive.zip")
                )
        );

        CompressionBenchmark benchmark = new CompressionBenchmark();
        List<BenchmarkResult> results = new ArrayList<>();

        for (BenchmarkCase benchmarkCase : cases) {
            for (Compressor compressor : compressors) {
                results.add(
                        benchmark.benchmark(
                                benchmarkCase,
                                compressor
                        )
                );
            }
        }

        BenchmarkConsoleVisualizer.title("RLE compression benchmark");
        BenchmarkConsoleVisualizer.results(results);

        BenchmarkConsoleVisualizer.emptyLine();
        BenchmarkConsoleVisualizer.conclusion();
    }
}