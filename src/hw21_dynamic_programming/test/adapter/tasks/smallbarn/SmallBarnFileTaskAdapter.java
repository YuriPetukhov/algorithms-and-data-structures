package hw21_dynamic_programming.test.adapter.tasks.smallbarn;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.test.adapter.support.TaskInputParsers;
import hw21_dynamic_programming.tasks.smallbarn.SmallBarnTaskModule;

public final class SmallBarnFileTaskAdapter
        implements FileTaskAdapter<boolean[][], Integer> {

    @Override
    public String taskId() {
        return SmallBarnTaskModule.TASK_ID;
    }

    @Override
    public Class<boolean[][]> inputType() {
        return boolean[][].class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public boolean[][] parse(String rawInput) {
        return TaskInputParsers.denseBlockedMatrix(rawInput);
    }

    @Override
    public String format(Integer result) {
        return result.toString();
    }
}
