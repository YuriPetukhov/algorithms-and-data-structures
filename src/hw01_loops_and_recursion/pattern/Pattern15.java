package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern15 implements Pattern {

    private final int offset;
    private final int bandWidth;
    private final char fillChar;
    private final char emptyChar;

    public Pattern15(int offset, int bandWidth, char fillChar, char emptyChar) {
        this.offset = offset;
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
                    int d = col - row;

                    boolean band1 = Math.abs(d - offset) <= bandWidth;
                    boolean band2 = Math.abs(d + offset) <= bandWidth;

                    return (band1 || band2) ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
