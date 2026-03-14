package hw09_linear_sorting.test.middle;

import hw09_linear_sorting.programs.binary_sorting.io.UInt16BinaryReader;

import java.nio.file.Path;

public final class BinarySortValidator {

    private BinarySortValidator() {
    }

    public static boolean isSorted(Path file) throws Exception {
        try (UInt16BinaryReader reader = new UInt16BinaryReader(file)) {
            int prev = reader.read();
            if (prev == -1) {
                return true;
            }

            int cur;
            while ((cur = reader.read()) != -1) {
                if (cur < prev) {
                    return false;
                }
                prev = cur;
            }
        }

        return true;
    }
}