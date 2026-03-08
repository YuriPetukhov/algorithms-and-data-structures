package hw08_quick_and_merge_sort.libs.sorting.algorithms.external;

import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io.IntLineReader;
import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io.IntLineWriter;

import java.io.IOException;
import java.nio.file.Path;

public final class ExternalSortES2 {

    private ExternalSortES2() {
    }

    public static int splitToRuns(Path input,
                                  Path f1,
                                  Path f2) throws IOException {

        try (IntLineReader reader = new IntLineReader(input);
             IntLineWriter w1 = new IntLineWriter(f1);
             IntLineWriter w2 = new IntLineWriter(f2)) {

            String line = reader.readLine();
            if (line == null) return 0;

            int runs = 1;
            boolean toFirst = true;

            int prev = Integer.parseInt(line.trim());
            (toFirst ? w1 : w2).writeLine(line);

            while ((line = reader.readLine()) != null) {
                int value = Integer.parseInt(line.trim());

                if (value < prev) {
                    runs++;
                    toFirst = !toFirst;
                }

                (toFirst ? w1 : w2).writeLine(line);
                prev = value;
            }

            return runs;
        }
    }

    public static void mergeRuns(Path f1,
                                 Path f2,
                                 Path output) throws IOException {

        try (IntLineReader r1 = new IntLineReader(f1);
             IntLineReader r2 = new IntLineReader(f2);
             IntLineWriter out = new IntLineWriter(output)) {

            String s1 = r1.readLine();
            String s2 = r2.readLine();

            while (s1 != null || s2 != null) {

                while (s1 != null && s2 != null) {

                    int v1 = Integer.parseInt(s1.trim());
                    int v2 = Integer.parseInt(s2.trim());

                    if (v1 <= v2) {
                        out.writeLine(s1);
                        s1 = r1.readLine();
                    } else {
                        out.writeLine(s2);
                        s2 = r2.readLine();
                    }
                }

                while (s1 != null) {
                    out.writeLine(s1);
                    s1 = r1.readLine();
                }

                while (s2 != null) {
                    out.writeLine(s2);
                    s2 = r2.readLine();
                }
            }
        }
    }
}
