package hw21_dynamic_programming.test.caseflow.steps;

import hw21_dynamic_programming.test.caseflow.CaseContext;
import hw21_dynamic_programming.test.caseflow.CaseStep;
import hw21_dynamic_programming.test.compare.TextNormalizer;

public final class NormalizeStep implements CaseStep {

    private final TextNormalizer normalizer;

    public NormalizeStep(TextNormalizer normalizer) {
        this.normalizer = normalizer;
    }

    @Override
    public void execute(CaseContext context) {
        context.expectedNormalized(normalizer.normalize(context.expectedRaw()));
        context.actualNormalized(normalizer.normalize(context.actualRaw()));
    }
}
