package hw20_rle_file_compression;

import hw20_rle_file_compression.cli.Command;
import hw20_rle_file_compression.cli.CommandParser;
import hw20_rle_file_compression.cli.UsagePrinter;
import hw20_rle_file_compression.libs.compression.FileProcessingResult;
import hw20_rle_file_compression.service.AlgorithmService;
import hw20_rle_file_compression.service.CompressionContext;
import hw20_rle_file_compression.service.Handler;
import hw20_rle_file_compression.service.safety.CompressionSafetyPolicy;
import hw20_rle_file_compression.service.steps.CompressStep;
import hw20_rle_file_compression.service.steps.DecompressStep;
import hw20_rle_file_compression.service.steps.ReadFileStep;
import hw20_rle_file_compression.service.steps.ValidateCompressionInputStep;
import hw20_rle_file_compression.service.steps.ValidateFileSizeStep;
import hw20_rle_file_compression.service.steps.ValidateOutputSizeStep;
import hw20_rle_file_compression.service.steps.WriteFileStep;
import hw20_rle_file_compression.visualization.BenchmarkConsoleVisualizer;

import java.util.ArrayList;
import java.util.List;

public class RleApplication {
    public static void main(String[] args) {
        if (args.length == 0) {
            UsagePrinter.print();
            return;
        }

        try {
            Command command = new CommandParser().parse(args);

            CompressionContext<FileProcessingResult> context =
                    new CompressionContext<>(
                            command.inputPath(),
                            command.outputPath(),
                            command.compressor(),
                            command.compress()
                    );

            CompressionSafetyPolicy policy = new CompressionSafetyPolicy(
                    10 * 1024 * 1024,
                    100 * 1024 * 1024
            );

            AlgorithmService<CompressionContext<FileProcessingResult>, FileProcessingResult> service =
                    new AlgorithmService<>(
                            new Handler<>(
                                    steps(command, policy)
                            )
                    );

            FileProcessingResult result = service.execute(context);

            BenchmarkConsoleVisualizer.title("RLE file processing result");
            BenchmarkConsoleVisualizer.processingResult(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            UsagePrinter.print();
        }
    }

    private static List<hw20_rle_file_compression.service.steps.Step<CompressionContext<FileProcessingResult>>> steps(
            Command command,
            CompressionSafetyPolicy policy
    ) {
        List<hw20_rle_file_compression.service.steps.Step<CompressionContext<FileProcessingResult>>> steps =
                new ArrayList<>();

        steps.add(new ValidateCompressionInputStep<>());
        steps.add(new ReadFileStep<>());
        steps.add(new ValidateFileSizeStep<>(policy));

        if (command.compress()) {
            steps.add(new CompressStep());
        } else {
            steps.add(new DecompressStep());
        }

        steps.add(new ValidateOutputSizeStep<>(policy));
        steps.add(new WriteFileStep());

        return steps;
    }
}