package hw08_quick_and_merge_sort.libs.sorting.algorithms.external;

import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io.IntLineReader;
import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io.IntLineWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExternalSortES3 {

    private ExternalSortES3() {}

    public static void presortBlocks(Path input,
                                     Path output,
                                     int blockSize) throws IOException {

        try (IntLineReader reader = new IntLineReader(input);
             IntLineWriter writer = new IntLineWriter(output)) {

            List<Integer> buffer = new ArrayList<>(blockSize);

            String line;

            while ((line = reader.readLine()) != null) {

                buffer.add(Integer.parseInt(line.trim()));

                if (buffer.size() == blockSize) {
                    flush(buffer, writer);
                }
            }

            if (!buffer.isEmpty()) {
                flush(buffer, writer);
            }
        }
    }

    private static void flush(List<Integer> buffer,
                              IntLineWriter writer) throws IOException {

        Collections.sort(buffer);

        for (int value : buffer) {
            writer.writeLine(Integer.toString(value));
        }

        buffer.clear();
    }
}
