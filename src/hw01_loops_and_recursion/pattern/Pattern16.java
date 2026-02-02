package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern16 implements Pattern {

    private final int radius;
    private final char fillChar;
    private final char emptyChar;

    public Pattern16(int radius, char fillChar, char emptyChar) {
        this.radius = radius;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int c = ctx.size() / 2;
                    int dist = Math.abs(row - c) + Math.abs(col - c);
                    return (dist <= radius) ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
