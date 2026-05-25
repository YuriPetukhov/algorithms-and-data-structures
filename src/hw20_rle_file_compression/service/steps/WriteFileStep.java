package hw20_rle_file_compression.service.steps;

import hw20_rle_file_compression.libs.compression.FileProcessingResult;
import hw20_rle_file_compression.service.CompressionContext;

import java.io.IOException;
import java.nio.file.Files;

public class WriteFileStep implements Step<CompressionContext<FileProcessingResult>> {
    @Override
    public void execute(CompressionContext<FileProcessingResult> context) {
        try {
            Files.write(context.outputPath(), context.outputData());
            context.setResult(context.processingResult());
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to write file: " + context.outputPath(),
                    e
            );
        }
    }
}