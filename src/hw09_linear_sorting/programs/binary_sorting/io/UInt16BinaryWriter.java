package hw09_linear_sorting.programs.binary_sorting.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class UInt16BinaryWriter implements AutoCloseable {

    private final OutputStream out;

    public UInt16BinaryWriter(Path file) throws IOException {
        this.out = new BufferedOutputStream(Files.newOutputStream(file));
    }

    public void write(int value) throws IOException {

        out.write((value >>> 8) & 0xFF);
        out.write(value & 0xFF);
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}