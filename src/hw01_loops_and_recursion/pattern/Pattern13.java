package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern13 implements Pattern {

    private final int bandWidth;
    private final char fillChar;
    private final char emptyChar;

    public Pattern13(int bandWidth, char fillChar, char emptyChar) {
        this.bandWidth = bandWidth;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int diag = ctx.size() - 1;
                    int dist = Math.abs((row + col) - diag);
                    return (dist <= bandWidth) ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
