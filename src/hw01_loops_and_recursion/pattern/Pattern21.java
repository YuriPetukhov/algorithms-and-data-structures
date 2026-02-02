package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern21 implements Pattern {

    private final int baseGapDots;
    private final char fillChar;
    private final char emptyChar;

    public Pattern21(int baseGapDots, char fillChar, char emptyChar) {
        if (baseGapDots < 0) throw new IllegalArgumentException("baseGapDots must be >= 0");
        this.baseGapDots = baseGapDots;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {

                    if (row == 0 || col == 0) return 0;

                    int step = (baseGapDots + 1) + row;
                    boolean inside = (col % step == 0);

                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
