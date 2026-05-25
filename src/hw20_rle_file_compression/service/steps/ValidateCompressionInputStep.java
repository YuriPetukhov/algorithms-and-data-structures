package hw20_rle_file_compression.service.steps;

import hw20_rle_file_compression.service.CompressionContext;

import java.nio.file.Files;

public class ValidateCompressionInputStep<R>
        implements Step<CompressionContext<R>> {

    @Override
    public void execute(CompressionContext<R> context) {
        if (context.inputPath() == null) {
            throw new IllegalArgumentException("Input path must not be null");
        }

        if (context.outputPath() == null) {
            throw new IllegalArgumentException("Output path must not be null");
        }

        if (context.compressor() == null) {
            throw new IllegalArgumentException("Compressor must not be null");
        }

        if (!Files.exists(context.inputPath())) {
            throw new IllegalArgumentException(
                    "Input file does not exist: " + context.inputPath()
            );
        }

        if (!Files.isRegularFile(context.inputPath())) {
            throw new IllegalArgumentException(
                    "Input path is not a file: " + context.inputPath()
            );
        }
    }
}