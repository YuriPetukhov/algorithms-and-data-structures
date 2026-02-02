package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern12 implements Pattern {

    private static final int BASE_SIZE = 25;

    private static final int[] WIDTH = {
            21, 20, 20, 20, 20, 20, 20, 19, 19, 18,
            18, 17, 17, 16, 15, 14, 13, 11,  9,  7,
            1,  0,  0,  0,  0
    };

    private final char fillChar;
    private final char emptyChar;

    public Pattern12(char fillChar, char emptyChar) {
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
