package hw20_rle_file_compression.service.steps;

import hw20_rle_file_compression.service.CompressionContext;

import java.io.IOException;
import java.nio.file.Files;

public class ReadFileStep<R>
        implements Step<CompressionContext<R>> {

    @Override
    public void execute(CompressionContext<R> context) {
        try {
            context.setInputData(
                    Files.readAllBytes(context.inputPath())
            );
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to read file: " + context.inputPath(),
                    e
            );
        }
    }
}