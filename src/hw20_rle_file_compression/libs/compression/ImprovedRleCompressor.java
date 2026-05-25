package hw20_rle_file_compression.libs.compression;

import java.io.ByteArrayOutputStream;

public class ImprovedRleCompressor implements Compressor {
    private static final int MAX_BLOCK_LENGTH = 127;
    private static final int MIN_RUN_LENGTH = 3;

    @Override
    public byte[] compress(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int index = 0;

        while (index < input.length) {
            int runLength = countRun(input, index);

            if (runLength >= MIN_RUN_LENGTH) {
                int length = Math.min(runLength, MAX_BLOCK_LENGTH);

                output.write(length);
                output.write(input[index]);

                index += length;
            } else {
                int rawStart = index;
                int rawLength = 0;

                while (
                        index < input.length
                                && rawLength < MAX_BLOCK_LENGTH
                                && countRun(input, index) < MIN_RUN_LENGTH
                ) {
                    index++;
                    rawLength++;
                }

                output.write(-rawLength);

                for (int i = 0; i < rawLength; i++) {
                    output.write(input[rawStart + i]);
                }
            }
        }

        return output.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int index = 0;

        while (index < input.length) {
            int control = input[index];
            index++;

            if (control > 0) {
                if (index >= input.length) {
                    throw new IllegalArgumentException("Corrupted compressed data");
                }

                byte value = input[index];
                index++;

                for (int i = 0; i < control; i++) {
                    output.write(value);
                }
            } else if (control < 0) {
                int length = -control;

                if (index + length > input.length) {
                    throw new IllegalArgumentException("Corrupted compressed data");
                }

                for (int i = 0; i < length; i++) {
                    output.write(input[index]);
                    index++;
                }
            } else {
                throw new IllegalArgumentException("Corrupted compressed data");
            }
        }

        return output.toByteArray();
    }

    @Override
    public String name() {
        return "Improved RLE";
    }

    private int countRun(byte[] input, int index) {
        byte value = input[index];
        int count = 1;

        while (
                index + count < input.length
                        && input[index + count] == value
                        && count < MAX_BLOCK_LENGTH
        ) {
            count++;
        }

        return count;
    }
}