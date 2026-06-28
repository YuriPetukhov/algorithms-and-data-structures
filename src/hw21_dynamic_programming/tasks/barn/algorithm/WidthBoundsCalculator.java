package hw21_dynamic_programming.tasks.barn.algorithm;

import hw21_dynamic_programming.tasks.barn.model.WidthBounds;

import java.util.ArrayDeque;
import java.util.Deque;

public final class WidthBoundsCalculator {

    public WidthBounds calculate(int[] heights) {
        int size = heights.length;
        int[] left = new int[size];
        int[] right = new int[size];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int index = 0; index < size; index++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[index]) {
                stack.pop();
            }
            left[index] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(index);
        }

        stack.clear();
        for (int index = size - 1; index >= 0; index--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[index]) {
                stack.pop();
            }
            right[index] = stack.isEmpty() ? size - 1 : stack.peek() - 1;
            stack.push(index);
        }
        return new WidthBounds(left, right);
    }
}
