package hw21_dynamic_programming.test.adapter.tasks.fiveeight;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.test.adapter.support.TaskInputParsers;
import hw21_dynamic_programming.tasks.fiveeight.FiveEightTaskModule;

import java.math.BigInteger;

public final class FiveEightFileTaskAdapter
        implements FileTaskAdapter<Integer, BigInteger> {

    @Override
    public String taskId() {
        return FiveEightTaskModule.TASK_ID;
    }

    @Override
    public Class<Integer> inputType() {
        return Integer.class;
    }

    @Override
    public Class<BigInteger> resultType() {
        return BigInteger.class;
    }

    @Override
    public Integer parse(String rawInput) {
        return TaskInputParsers.singleInteger(rawInput, "N");
    }

    @Override
    public String format(BigInteger result) {
        return result.toString();
    }
}
