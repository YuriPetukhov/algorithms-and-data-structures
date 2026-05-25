package hw20_rle_file_compression.service.steps;

import hw20_rle_file_compression.libs.compression.FileProcessingResult;
import hw20_rle_file_compression.service.CompressionContext;

public class DecompressStep implements Step<CompressionContext<FileProcessingResult>> {
    @Override
    public void execute(CompressionContext<FileProcessingResult> context) {
        byte[] decompressed = context.compressor().decompress(context.inputData());

        context.setOutputData(decompressed);

        context.setProcessingResult(
                FileProcessingResult.of(
                        context.compressor().name(),
                        "decompress",
                        context.inputData().length,
                        decompressed.length
                )
        );
    }
}