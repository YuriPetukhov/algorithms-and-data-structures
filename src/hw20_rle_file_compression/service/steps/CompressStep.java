package hw20_rle_file_compression.service.steps;

import hw20_rle_file_compression.libs.compression.FileProcessingResult;
import hw20_rle_file_compression.service.CompressionContext;

public class CompressStep implements Step<CompressionContext<FileProcessingResult>> {
    @Override
    public void execute(CompressionContext<FileProcessingResult> context) {
        byte[] compressed = context.compressor().compress(context.inputData());

        context.setOutputData(compressed);

        context.setProcessingResult(
                FileProcessingResult.of(
                        context.compressor().name(),
                        "compress",
                        context.inputData().length,
                        compressed.length
                )
        );
    }
}