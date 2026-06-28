package hw21_dynamic_programming.test.adapter.tasks.bigbarn;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.test.adapter.support.TaskInputParsers;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.bigbarn.BigBarnTaskModule;

public final class BigBarnFileTaskAdapter
        implements FileTaskAdapter<BarnInput, Integer> {

    @Override
    public String taskId() {
        return BigBarnTaskModule.TASK_ID;
    }

    @Override
    public Class<BarnInput> inputType() {
        return BarnInput.class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public BarnInput parse(String rawInput) {
        return TaskInputParsers.sparseBarnInput(rawInput);
    }

    @Override
    public String format(Integer result) {
        return result.toString();
    }
}
