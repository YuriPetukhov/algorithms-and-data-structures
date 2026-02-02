package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern22 implements Pattern {

    private final int runFill;
    private final int runEmpty;
    private final char fillChar;
    private final char emptyChar;

    public Pattern22(int runFill, int runEmpty, char fillChar, char emptyChar) {
        if (runFill <= 0) throw new IllegalArgumentException("runFill must be > 0");
        if (runEmpty <= 0) throw new IllegalArgumentException("runEmpty must be > 0");
        this.runFill = runFill;
        this.runEmpty = runEmpty;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int n = ctx.size();

                    int period = runFill + runEmpty;
                    int k = row * n + col;
                    int pos = k % period;

                    boolean inside = pos < runFill;
                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
