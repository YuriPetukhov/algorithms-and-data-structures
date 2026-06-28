package hw21_dynamic_programming.test.adapter.tasks.islandcount;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.test.adapter.support.TaskInputParsers;
import hw21_dynamic_programming.tasks.islandcount.IslandCountTaskModule;

public final class IslandCountFileTaskAdapter
        implements FileTaskAdapter<int[][], Integer> {

    @Override
    public String taskId() {
        return IslandCountTaskModule.TASK_ID;
    }

    @Override
    public Class<int[][]> inputType() {
        return int[][].class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public int[][] parse(String rawInput) {
        return TaskInputParsers.squareIntegerMatrix(rawInput);
    }

    @Override
    public String format(Integer result) {
        return result.toString();
    }
}
