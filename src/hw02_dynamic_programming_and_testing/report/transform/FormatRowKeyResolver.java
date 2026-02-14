package hw02_dynamic_programming_and_testing.report.transform;

import hw02_dynamic_programming_and_testing.report.model.TestCaseRef;

public class FormatRowKeyResolver implements RowKeyResolver {
    private final RowKeyFormat format;
    public FormatRowKeyResolver(RowKeyFormat format) { this.format = format; }
    @Override public String resolve(TestCaseRef ref) throws Exception { return format.resolve(ref); }
}

