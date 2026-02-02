package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern17 implements Pattern {

    private static final int BASE_SIZE = 25;

    private static final int START_H = 9;

    private static final int[] LEN =  {
            1, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1
    };
    private static final int[] DELTA ={
            -3,-2,-1, 0, +2,+3,+2,+1, 0,-1,-2,-3,-2,-3,-1, 0
    };

    private final char fillChar;
    private final char emptyChar;

    public Pattern17(char fillChar, char emptyChar) {
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    if (row >= BASE_SIZE || col >= BASE_SIZE) return 1;

                    int h = heightAtCol(col);

                    boolean inside = row >= (BASE_SIZE - h);

                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }

    private static int heightAtCol(int col) {
        int h = START_H;
        int x = 0;

        if (col == 0) return h;

        for (int k = 0; k < LEN.length; k++) {
            int len = LEN[k];
            int d = DELTA[k];

            for (int i = 0; i < len; i++) {
                x++;
                h += d;
                if (x == col) return h;
                if (x >= BASE_SIZE - 1) return h;
            }
        }

        return h;
    }
}
