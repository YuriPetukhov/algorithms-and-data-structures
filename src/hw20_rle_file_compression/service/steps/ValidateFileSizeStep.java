package hw20_rle_file_compression.service.steps;

import hw20_rle_file_compression.service.CompressionContext;
import hw20_rle_file_compression.service.safety.CompressionSafetyPolicy;

public class ValidateFileSizeStep<R> implements Step<CompressionContext<R>> {
    private final CompressionSafetyPolicy policy;

    public ValidateFileSizeStep(CompressionSafetyPolicy policy) {
        this.policy = policy;
    }

    @Override
    public void execute(CompressionContext<R> context) {
        if (context.inputData() == null) {
            throw new IllegalStateException("Input data must be read before size validation");
        }

        if (context.inputData().length > policy.maxInputSizeBytes()) {
            throw new IllegalStateException(
                    "Input file is too large: " + context.inputData().length
            );
        }
    }
}