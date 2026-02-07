package hw02_dynamic_programming_and_testing.test.caseflow;

public class ExecuteTaskStep implements CaseStep {

    @Override
    public void execute(CaseContext ctx) throws Exception {
        long start = System.nanoTime();
        String actual = ctx.task().run(ctx.inputRaw());
        long time = System.nanoTime() - start;

        ctx.setActualRaw(actual);
        ctx.setTimeNanos(time);
    }
}
