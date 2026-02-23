package hw06_sorting_algorithms.libs.sorting.ops;

import java.util.Objects;
import java.util.concurrent.CancellationException;

public final class InterruptibleIntArrayOps implements IntArrayOps {

    private final IntArrayOps delegate;
    private final String message;

    public InterruptibleIntArrayOps(IntArrayOps delegate) {
        this(delegate, "Interrupted");
    }

    public InterruptibleIntArrayOps(IntArrayOps delegate, String message) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
        this.message = (message == null || message.isBlank()) ? "Interrupted" : message;
    }

    private static void checkInterrupted(String message) {
        if (Thread.currentThread().isInterrupted()) {
            throw new CancellationException(message);
        }
    }

    @Override
    public boolean gt(int[] array, int leftIndex, int rightIndex) {
        checkInterrupted(message);
        return delegate.gt(array, leftIndex, rightIndex);
    }

    @Override
    public void swap(int[] array, int leftIndex, int rightIndex) {
        checkInterrupted(message);
        delegate.swap(array, leftIndex, rightIndex);
    }

    @Override
    public void write(int[] array, int index, int value) {
        checkInterrupted(message);
        delegate.write(array, index, value);
    }
}
