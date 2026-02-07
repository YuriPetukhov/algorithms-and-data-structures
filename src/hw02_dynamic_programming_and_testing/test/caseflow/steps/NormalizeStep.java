package hw02_dynamic_programming_and_testing.test.caseflow.steps;

import hw02_dynamic_programming_and_testing.test.caseflow.CaseContext;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseStep;
import hw02_dynamic_programming_and_testing.test.compare.TextNormalizationStrategy;

public class NormalizeStep implements CaseStep {

    private final TextNormalizationStrategy normalization;

    public NormalizeStep(TextNormalizationStrategy normalization) {
        this.normalization = normalization;
    }

    @Override
    public void execute(CaseContext ctx) {
        ctx.setExpectedNorm(normalization.normalize(ctx.expectedRaw()));
        ctx.setActualNorm(normalization.normalize(ctx.actualRaw()));
    }
}
