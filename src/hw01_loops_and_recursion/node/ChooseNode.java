package hw01_loops_and_recursion.node;

import hw01_loops_and_recursion.picker.Picker;
import hw01_loops_and_recursion.context.PatternContext;

import java.util.List;
import java.util.Objects;

public final class ChooseNode implements Node {
    private final Picker picker;
    private final List<Node> options;

    public ChooseNode(Picker picker, List<Node> options) {
        this.picker = Objects.requireNonNull(picker, "picker");
        this.options = List.copyOf(Objects.requireNonNull(options, "options"));

        if (this.options.isEmpty()) {
            throw new IllegalArgumentException("options must not be empty");
        }
        if (this.options.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("options must not contain nulls");
        }
    }

    @Override
    public char charAt(int row, int col, PatternContext ctx) {
        int idx = picker.pick(row, col, ctx);

        if (idx < 0 || idx >= options.size()) {
            throw new IllegalStateException(
                    "Picker returned index " + idx + " but options size is " + options.size()
            );
        }

        return options.get(idx).charAt(row, col, ctx);
    }
}
