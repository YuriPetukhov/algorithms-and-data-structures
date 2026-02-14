package hw02_dynamic_programming_and_testing.report.transform;

import hw02_dynamic_programming_and_testing.report.model.TestCaseRef;

public class SingleNumberRowKeyFormat implements RowKeyFormat {

    @Override public String id() { return "single_number"; }

    @Override
    public String resolve(TestCaseRef ref) throws Exception {
        String raw = java.nio.file.Files.readString(
                ref.inputsDir().resolve(ref.testName() + ref.inputExt())
        ).trim();

        String[] tok = raw.split("\\s+");
        if (tok.length == 0) return "";
        return tok[0];
    }
}

