package hw02_dynamic_programming_and_testing.report.transform;

import hw02_dynamic_programming_and_testing.report.model.TestCaseRef;

public class TwoTokensRowKeyFormat implements RowKeyFormat {

    @Override public String id() { return "two_tokens"; }

    @Override
    public String resolve(TestCaseRef ref) throws Exception {
        String raw = java.nio.file.Files.readString(
                ref.inputsDir().resolve(ref.testName() + ref.inputExt())
        ).trim();

        String[] tok = raw.split("\\s+");
        if (tok.length < 2) {
            throw new IllegalArgumentException("Expected 2 tokens in " + ref.testName());
        }
        return tok[0] + " " + tok[1];
    }
}

