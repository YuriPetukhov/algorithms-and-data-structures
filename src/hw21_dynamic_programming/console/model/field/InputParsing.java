package hw21_dynamic_programming.console.model.field;

public final class InputParsing {

    private InputParsing() {
    }

    public static int[] parseExactIntegers(String line, int expectedSize) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("строка не должна быть пустой.");
        }

        String[] tokens = trimmed.split("\\s+");
        if (tokens.length != expectedSize) {
            throw new IllegalArgumentException(
                    "ожидалось %d чисел, получено %d."
                            .formatted(expectedSize, tokens.length)
            );
        }

        int[] values = new int[expectedSize];
        for (int index = 0; index < expectedSize; index++) {
            try {
                values[index] = Integer.parseInt(tokens[index]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException(
                        "'%s' не является целым числом."
                                .formatted(tokens[index]),
                        exception
                );
            }
        }
        return values;
    }

    public static void requireRange(
            int value,
            int minimum,
            int maximum,
            String name
    ) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(
                    "%s должно находиться в диапазоне от %d до %d."
                            .formatted(name, minimum, maximum)
            );
        }
    }

    public static void requireBinary(int[] values) {
        for (int value : values) {
            if (value != 0 && value != 1) {
                throw new IllegalArgumentException(
                        "разрешены только значения 0 и 1."
                );
            }
        }
    }
}
