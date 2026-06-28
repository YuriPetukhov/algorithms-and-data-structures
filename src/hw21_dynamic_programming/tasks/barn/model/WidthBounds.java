package hw21_dynamic_programming.tasks.barn.model;

import java.util.Objects;

public final class WidthBounds {

    private final int[] left;
    private final int[] right;

    public WidthBounds(int[] left, int[] right) {
        Objects.requireNonNull(left, "Left bounds must not be null.");
        Objects.requireNonNull(right, "Right bounds must not be null.");
        if (left.length != right.length) {
            throw new IllegalArgumentException("Bounds arrays must have equal length.");
        }
        this.left = left.clone();
        this.right = right.clone();
    }

    public int size() {
        return left.length;
    }

    public int leftAt(int index) {
        return left[index];
    }

    public int rightAt(int index) {
        return right[index];
    }

    public int[] left() {
        return left.clone();
    }

    public int[] right() {
        return right.clone();
    }
}
