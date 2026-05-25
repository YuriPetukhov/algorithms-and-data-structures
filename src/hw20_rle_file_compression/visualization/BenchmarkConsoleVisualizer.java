package hw20_rle_file_compression.visualization;

import hw20_rle_file_compression.benchmark.BenchmarkResult;
import hw20_rle_file_compression.libs.compression.FileProcessingResult;

import java.util.List;

public final class BenchmarkConsoleVisualizer {
    private BenchmarkConsoleVisualizer() {
    }

    public static void processingResult(FileProcessingResult result) {
        System.out.println("Algorithm: " + result.algorithm());
        System.out.println("Operation: " + result.operation());
        System.out.println("Input size: " + result.inputSize());
        System.out.println("Output size: " + result.outputSize());
        System.out.printf(
                "Ratio: %.4f (%.2f%%)%n",
                result.ratio(),
                result.ratio() * 100
        );
    }

    public static void title(String title) {
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
    }

    public static void results(List<BenchmarkResult> results) {
        printHeader();

        for (BenchmarkResult result : results) {
            printRow(result);
        }
    }

    public static void conclusion() {
        System.out.println("Conclusion:");
        System.out.println("Simple RLE works well on data with long repeated byte sequences.");
        System.out.println("Improved RLE avoids strong expansion on data without long repetitions by storing raw blocks.");
        System.out.println("Already compressed files, such as zip archives, photos or audio, usually compress poorly with RLE.");
        System.out.println("Text files may compress better when they contain repeated characters or simple patterns.");
    }

    public static void emptyLine() {
        System.out.println();
    }

    private static void printHeader() {
        System.out.printf(
                "%-20s %-15s %15s %15s %12s %12s %10s%n",
                "Case",
                "Algorithm",
                "Original bytes",
                "Compressed",
                "Ratio",
                "Percent",
                "Restored"
        );

        System.out.println("-".repeat(115));
    }

    private static void printRow(BenchmarkResult result) {
        System.out.printf(
                "%-20s %-15s %15d %15d %12.4f %11.2f%% %10s%n",
                result.caseName(),
                result.algorithm(),
                result.originalSize(),
                result.compressedSize(),
                result.compressionRatio(),
                result.compressionRatio() * 100,
                result.restoredCorrectly() ? "OK" : "FAILED"
        );
    }
}