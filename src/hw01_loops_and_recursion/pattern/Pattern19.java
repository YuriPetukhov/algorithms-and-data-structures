package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern19 implements Pattern {

    private final int thickness;
    private final char fillChar;
    private final char emptyChar;

    public Pattern19(int thickness, char fillChar, char emptyChar) {
        if (thickness <= 0) throw new IllegalArgumentException("thickness must be > 0");
        this.thickness = thickness;
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
                    int t = thickness;

                    boolean inside =
                            row < t || row >= n - t ||
                                    col < t || col >= n - t;

                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
