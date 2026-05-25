package hw20_rle_file_compression.libs.compression;

public interface Compressor {
    byte[] compress(byte[] input);

    byte[] decompress(byte[] input);

    String name();
}