package hw08_quick_and_merge_sort.programs.external_sorting.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public final class RandomIntFileGenerator {

    private RandomIntFileGenerator() {}

    public static void generate(Path output,
                                int n,
                                int t) throws IOException {

        Random random = new Random();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(output)) {

            for (int i = 0; i < n; i++) {
                int value = random.nextInt(t) + 1;
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        }
    }
}
