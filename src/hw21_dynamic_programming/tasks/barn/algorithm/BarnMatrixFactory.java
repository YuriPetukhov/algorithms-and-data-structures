package hw21_dynamic_programming.tasks.barn.algorithm;

import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.barn.model.Cell;

public final class BarnMatrixFactory {

    public boolean[][] create(BarnInput input) {
        boolean[][] blocked = new boolean[input.height()][input.width()];
        for (Cell cell : input.blockedCells()) {
            blocked[cell.y()][cell.x()] = true;
        }
        return blocked;
    }
}
