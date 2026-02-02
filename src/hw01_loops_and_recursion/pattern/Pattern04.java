package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern04 implements Pattern {

    private final int topMargin;
    private final char fillChar;
    private final char emptyChar;

    public Pattern04(int topMargin, char fillChar, char emptyChar) {
        this.topMargin = topMargin;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    if (row < topMargin) {
                        return 0;
                    }
                    int emptyCount = row - topMargin + 1;
                    int threshold = ctx.size() - emptyCount;
                    return (col >= threshold ? 1 : 0);
                },
                List.of(fill, empty)
        );
    }
}
