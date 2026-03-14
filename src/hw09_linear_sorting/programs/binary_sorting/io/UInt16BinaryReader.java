package hw09_linear_sorting.programs.binary_sorting.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class UInt16BinaryReader implements AutoCloseable {

    private final InputStream in;

    public UInt16BinaryReader(Path file) throws IOException {
        this.in = new BufferedInputStream(Files.newInputStream(file));
    }

    public int read() throws IOException {

        int hi = in.read();
        if (hi < 0) return -1;

        int lo = in.read();
        if (lo < 0) throw new IOException("Unexpected EOF");

        return (hi << 8) | lo;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}