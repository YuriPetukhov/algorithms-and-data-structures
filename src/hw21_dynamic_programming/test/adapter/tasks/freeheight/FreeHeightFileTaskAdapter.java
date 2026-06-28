package hw21_dynamic_programming.test.adapter.tasks.freeheight;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.test.adapter.support.PlainTextFormats;
import hw21_dynamic_programming.test.adapter.support.TaskInputParsers;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.freeheight.FreeHeightTaskModule;

public final class FreeHeightFileTaskAdapter
        implements FileTaskAdapter<BarnInput, int[][]> {

    @Override
    public String taskId() {
        return FreeHeightTaskModule.TASK_ID;
    }

    @Override
    public Class<BarnInput> inputType() {
        return BarnInput.class;
    }

    @Override
    public Class<int[][]> resultType() {
        return int[][].class;
    }

    @Override
    public BarnInput parse(String rawInput) {
        return TaskInputParsers.sparseBarnInput(rawInput);
    }

    @Override
    public String format(int[][] result) {
        return PlainTextFormats.integerMatrix(result);
    }
}
