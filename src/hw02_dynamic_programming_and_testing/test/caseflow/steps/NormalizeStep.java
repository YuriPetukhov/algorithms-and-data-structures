package hw02_dynamic_programming_and_testing.test.caseflow;

import hw02_dynamic_programming_and_testing.test.compare.TextNormalizer;

public class NormalizeStep implements CaseStep {

    private final TextNormalizer normalizer;

    public NormalizeStep(TextNormalizer normalizer) {
        this.normalizer = normalizer;
    }

    @Override
    public void execute(CaseContext ctx) {
        ctx.setInputNorm(normalizer.normalize(ctx.inputRaw()));
        ctx.setExpectedNorm(normalizer.normalize(ctx.expectedRaw()));
        ctx.setActualNorm(normalizer.normalize(ctx.actualRaw()));
    }
}
