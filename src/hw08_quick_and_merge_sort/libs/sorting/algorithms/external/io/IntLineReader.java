package hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class IntLineReader implements Closeable {

    private final BufferedReader reader;

    public IntLineReader(Path path) throws IOException {
        this.reader = Files.newBufferedReader(path);
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
