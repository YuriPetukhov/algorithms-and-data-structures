package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern20 implements Pattern {

    private final int gapDots;
    private final char fillChar;
    private final char emptyChar;

    public Pattern20(int gapDots, char fillChar, char emptyChar) {
        if (gapDots < 0) throw new IllegalArgumentException("gapDots must be >= 0");
        this.gapDots = gapDots;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int step = gapDots + 1;
                    boolean inside = (row % step == 0) && (col % step == 0);
                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
