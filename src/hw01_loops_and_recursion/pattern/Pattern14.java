package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern14 implements Pattern {

    private static final int BASE_SIZE = 25;

    private static final int[] WIDTH = {
            25, 25, 25, 25, 25, 21, 17, 15, 13, 12,
            11, 10, 9, 8, 8, 7, 7, 6, 6, 6,
            6, 5, 5, 5, 5
    };

    private final char fillChar;
    private final char emptyChar;

    public Pattern14(char fillChar, char emptyChar) {
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    if (row >= BASE_SIZE || col >= BASE_SIZE) {
                        return 1;
                    }

                    boolean inside =
                            (col < WIDTH[row]) &&
                                    (row < WIDTH[col]);

                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
