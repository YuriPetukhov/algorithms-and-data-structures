package hw08_quick_and_merge_sort.libs.sorting.algorithms.external.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class IntLineWriter implements Closeable {

    private final BufferedWriter writer;

    public IntLineWriter(Path path) throws IOException {
        this.writer = Files.newBufferedWriter(path);
    }

    public void writeLine(String value) throws IOException {
        writer.write(value);
        writer.newLine();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
