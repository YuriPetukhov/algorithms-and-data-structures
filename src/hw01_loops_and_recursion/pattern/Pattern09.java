package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern09 implements Pattern {

    private final int offset;
    private final char fillChar;
    private final char emptyChar;

    public Pattern09(int offset, char fillChar, char emptyChar) {
        this.offset = offset;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int dist = Math.abs(row - col);
                    return (dist >= offset) ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
