package hw08_quick_and_merge_sort.libs.sorting.algorithms.external;

import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io.IntLineReader;
import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io.IntLineWriter;

import java.io.IOException;
import java.nio.file.Path;

public final class ExternalSortES1 {

    private ExternalSortES1() {}

    public static void sort(Path input,
                            Path output,
                            int t,
                            Path[] buckets) throws IOException {

        try (IntLineReader reader = new IntLineReader(input)) {

            IntLineWriter[] writers = new IntLineWriter[t + 1];

            try {
                for (int i = 1; i <= t; i++) {
                    writers[i] = new IntLineWriter(buckets[i]);
                }

                String line;
                while ((line = reader.readLine()) != null) {
                    int value = Integer.parseInt(line.trim());
                    writers[value].writeLine(line);
                }

            } finally {
                for (int i = 1; i <= t; i++) {
                    if (writers[i] != null) {
                        writers[i].close();
                    }
                }
            }
        }

        try (IntLineWriter out = new IntLineWriter(output)) {

            for (int i = 1; i <= t; i++) {
                try (IntLineReader bucketReader =
                             new IntLineReader(buckets[i])) {

                    String line;
                    while ((line = bucketReader.readLine()) != null) {
                        out.writeLine(line);
                    }
                }
            }
        }
    }
}
