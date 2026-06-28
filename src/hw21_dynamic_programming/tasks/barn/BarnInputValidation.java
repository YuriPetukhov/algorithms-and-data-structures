package hw21_dynamic_programming.tasks.barn;

import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.barn.model.Cell;

import java.util.HashSet;
import java.util.Set;

public final class BarnInputValidation {

    private BarnInputValidation() {
    }

    public static void validateDense(
            boolean[][] blocked,
            int maximumWidth,
            int maximumHeight
    ) {
        if (blocked.length == 0 || blocked[0] == null || blocked[0].length == 0) {
            throw new IllegalArgumentException("Карта фермы не должна быть пустой.");
        }
        if (blocked.length > maximumHeight || blocked[0].length > maximumWidth) {
            throw new IllegalArgumentException(
                    "Размер карты не должен превышать %d × %d."
                            .formatted(maximumWidth, maximumHeight)
            );
        }

        int width = blocked[0].length;
        for (boolean[] row : blocked) {
            if (row == null || row.length != width) {
                throw new IllegalArgumentException("Карта фермы должна быть прямоугольной.");
            }
        }
    }

    public static void validateSparse(BarnInput input) {
        if (input.width() < 1 || input.width() > 1000
                || input.height() < 1 || input.height() > 1000) {
            throw new IllegalArgumentException(
                    "Размеры N и M должны находиться в диапазоне от 1 до 1000."
            );
        }
        if (input.blockedCells().size() > 10_000) {
            throw new IllegalArgumentException(
                    "Количество построек T не должно превышать 10000."
            );
        }

        Set<Long> coordinates = new HashSet<>();
        for (Cell cell : input.blockedCells()) {
            if (cell == null) {
                throw new IllegalArgumentException("Координата постройки не должна быть null.");
            }
            if (cell.x() < 0 || cell.x() >= input.width()
                    || cell.y() < 0 || cell.y() >= input.height()) {
                throw new IllegalArgumentException(
                        "Координата (%d, %d) выходит за границы поля %d × %d."
                                .formatted(
                                        cell.x(),
                                        cell.y(),
                                        input.width(),
                                        input.height()
                                )
                );
            }
            long key = ((long) cell.y() << 32) ^ (cell.x() & 0xffffffffL);
            if (!coordinates.add(key)) {
                throw new IllegalArgumentException(
                        "Координата постройки (%d, %d) указана повторно."
                                .formatted(cell.x(), cell.y())
                );
            }
        }
    }
}
