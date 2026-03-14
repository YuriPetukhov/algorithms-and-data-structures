package hw09_linear_sorting.programs.binary_sorting.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public final class UInt16RandomBinaryFileGenerator {

    private static final int MAX_VALUE = 65535;
    private static final int DEFAULT_BUFFER_VALUES = 1_000_000;

    private UInt16RandomBinaryFileGenerator() {
    }

    public static void generate(Path file, long count) throws IOException {
        generate(file, count, DEFAULT_BUFFER_VALUES, new Random());
    }

    public static void generate(Path file, long count, int bufferValues, Random random)
            throws IOException {

        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }

        byte[] buffer = new byte[bufferValues * 2];

        try (OutputStream out =
                     new BufferedOutputStream(Files.newOutputStream(file), buffer.length)) {

            long written = 0;

            while (written < count) {
                int valuesInChunk = (int) Math.min(bufferValues, count - written);

                int p = 0;

                for (int i = 0; i < valuesInChunk; i++) {
                    int value = random.nextInt(MAX_VALUE + 1);

                    buffer[p++] = (byte) ((value >>> 8) & 0xFF);
                    buffer[p++] = (byte) (value & 0xFF);
                }

                out.write(buffer, 0, valuesInChunk * 2);
                written += valuesInChunk;
            }
        }
    }
}