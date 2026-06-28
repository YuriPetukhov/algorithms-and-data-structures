package hw21_dynamic_programming.tasks.barn.model;

import java.util.List;
import java.util.Objects;

public record BarnInput(
        int width,
        int height,
        List<Cell> blockedCells
) {

    public BarnInput {
        Objects.requireNonNull(blockedCells, "Blocked cells must not be null.");
        blockedCells = List.copyOf(blockedCells);
    }
}
