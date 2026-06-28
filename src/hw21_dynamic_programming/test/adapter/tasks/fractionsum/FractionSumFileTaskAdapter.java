package hw21_dynamic_programming.test.adapter.tasks.fractionsum;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.tasks.fractionsum.FractionSumTaskModule;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionInput;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FractionSumFileTaskAdapter
        implements FileTaskAdapter<FractionInput, FractionResult> {

    private static final Pattern EXPRESSION = Pattern.compile(
            "\\s*(\\d+)\\s*/\\s*(\\d+)\\s*\\+\\s*(\\d+)\\s*/\\s*(\\d+)\\s*"
    );

    @Override
    public String taskId() {
        return FractionSumTaskModule.TASK_ID;
    }

    @Override
    public Class<FractionInput> inputType() {
        return FractionInput.class;
    }

    @Override
    public Class<FractionResult> resultType() {
        return FractionResult.class;
    }

    @Override
    public FractionInput parse(String rawInput) {
        Matcher matcher = EXPRESSION.matcher(rawInput);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expected input in format a/b+c/d, for example 1/2+1/3."
            );
        }
        return new FractionInput(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))
        );
    }

    @Override
    public String format(FractionResult result) {
        return result.toString();
    }
}
