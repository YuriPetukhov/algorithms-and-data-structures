package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern24 implements Pattern {

    private final char fillChar;
    private final char emptyChar;

    public Pattern24(char fillChar, char emptyChar) {
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
                    boolean inside = (row == col) || (row + col == n - 1);
                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
