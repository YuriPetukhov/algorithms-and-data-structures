package hw20_rle_file_compression.libs.compression;

import java.io.ByteArrayOutputStream;

public class RleCompressor implements Compressor {
    private static final int MAX_RUN_LENGTH = 255;

    @Override
    public byte[] compress(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        if (input.length == 0) {
            return output.toByteArray();
        }

        byte current = input[0];
        int count = 1;

        for (int i = 1; i < input.length; i++) {
            if (input[i] == current && count < MAX_RUN_LENGTH) {
                count++;
            } else {
                output.write(count);
                output.write(current);

                current = input[i];
                count = 1;
            }
        }

        output.write(count);
        output.write(current);

        return output.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] input) {
        if (input.length % 2 != 0) {
            throw new IllegalArgumentException("Corrupted compressed data");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        for (int index = 0; index < input.length; index += 2) {
            int count = input[index] & 0xFF;

            if (count == 0) {
                throw new IllegalArgumentException("Corrupted compressed data");
            }

            byte value = input[index + 1];

            for (int i = 0; i < count; i++) {
                output.write(value);
            }
        }

        return output.toByteArray();
    }

    @Override
    public String name() {
        return "RLE";
    }
}