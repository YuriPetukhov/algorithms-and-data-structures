package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern10 implements Pattern {

    private final char fillChar;
    private final char emptyChar;

    public Pattern10(char fillChar, char emptyChar) {
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int size = ctx.size();

                    int start = row + 1;
                    int len = row + 1;
                    int end = start + len;

                    int left = Math.max(start, 0);
                    int right = Math.min(end, size);

                    boolean inside = (col >= left && col < right);
                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
