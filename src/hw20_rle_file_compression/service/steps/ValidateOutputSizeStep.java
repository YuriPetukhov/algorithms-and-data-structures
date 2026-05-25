package hw20_rle_file_compression.service.steps;

import hw20_rle_file_compression.service.CompressionContext;
import hw20_rle_file_compression.service.safety.CompressionSafetyPolicy;

public class ValidateOutputSizeStep<R> implements Step<CompressionContext<R>> {
    private final CompressionSafetyPolicy policy;

    public ValidateOutputSizeStep(CompressionSafetyPolicy policy) {
        this.policy = policy;
    }

    @Override
    public void execute(CompressionContext<R> context) {
        if (context.outputData() == null) {
            throw new IllegalStateException("Output data must be produced before size validation");
        }

        if (context.outputData().length > policy.maxOutputSizeBytes()) {
            throw new IllegalStateException(
                    "Output data is too large: " + context.outputData().length
            );
        }
    }
}