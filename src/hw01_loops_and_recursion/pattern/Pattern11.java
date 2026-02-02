package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern11 implements Pattern {

    private final int margin;
    private final char fillChar;
    private final char emptyChar;

    public Pattern11(int margin, char fillChar, char emptyChar) {
        this.margin = margin;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int last = ctx.size() - 1;
                    int m = margin;

                    boolean isLine =
                            row == m ||
                                    row == last - m ||
                                    col == m ||
                                    col == last - m;

                    return isLine ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}

