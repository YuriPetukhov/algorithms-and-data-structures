package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern18 implements Pattern {

    private final int thickness;
    private final char fillChar;
    private final char emptyChar;

    public Pattern18(int thickness, char fillChar, char emptyChar) {
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
                    int t = thickness;

                    boolean stripes = (row < t) || (col < t);
                    boolean cornerDot = (row == 0 && col == 0);

                    boolean inside = stripes && !cornerDot;
                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
