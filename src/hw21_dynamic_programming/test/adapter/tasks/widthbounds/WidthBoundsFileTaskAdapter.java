package hw21_dynamic_programming.test.adapter.tasks.widthbounds;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.test.adapter.support.PlainTextFormats;
import hw21_dynamic_programming.test.adapter.support.TaskInputParsers;
import hw21_dynamic_programming.tasks.barn.model.WidthBounds;
import hw21_dynamic_programming.tasks.widthbounds.WidthBoundsTaskModule;

public final class WidthBoundsFileTaskAdapter
        implements FileTaskAdapter<int[], WidthBounds> {

    @Override
    public String taskId() {
        return WidthBoundsTaskModule.TASK_ID;
    }

    @Override
    public Class<int[]> inputType() {
        return int[].class;
    }

    @Override
    public Class<WidthBounds> resultType() {
        return WidthBounds.class;
    }

    @Override
    public int[] parse(String rawInput) {
        return TaskInputParsers.sizedIntegerSequence(rawInput);
    }

    @Override
    public String format(WidthBounds result) {
        return PlainTextFormats.integerArray(result.left())
                + System.lineSeparator()
                + PlainTextFormats.integerArray(result.right());
    }
}
