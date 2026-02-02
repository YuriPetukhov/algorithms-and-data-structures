package hw01_loops_and_recursion.render;

import hw01_loops_and_recursion.context.PatternContext;
import hw01_loops_and_recursion.node.Node;

public class Renderer {
    public static void print(int size, Node root) {
        PatternContext ctx = new PatternContext(size);

        for (int row = 0; row < size; row++) {
            StringBuilder line = new StringBuilder(size);
            for (int col = 0; col < size; col++) {
                line.append(root.charAt(row, col, ctx)).append(' ');
            }
            System.out.println(line);
        }
    }
}
