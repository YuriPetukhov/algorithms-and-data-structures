package hw20_rle_file_compression.cli;

public final class UsagePrinter {
    private UsagePrinter() {
    }

    public static void print() {
        System.out.println("RLE file compression utility");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  compress <simple|improved> <input-file> <output-file>");
        System.out.println("  decompress <simple|improved> <input-file> <output-file>");
        System.out.println();
        System.out.println("Input file:");
        System.out.println("  You can use any existing file path.");
        System.out.println("  The file may be placed anywhere, for example in:");
        System.out.println("    src/hw20_rle_file_compression/demo/files/");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  compress simple src/hw20_rle_file_compression/demo/files/sample.txt src/hw20_rle_file_compression/demo/files/sample.rle");
        System.out.println("  decompress simple src/hw20_rle_file_compression/demo/files/sample.rle src/hw20_rle_file_compression/demo/files/sample_restored.txt");
        System.out.println();
        System.out.println("  compress improved src/hw20_rle_file_compression/demo/files/sample.txt src/hw20_rle_file_compression/demo/files/sample.irle");
        System.out.println("  decompress improved src/hw20_rle_file_compression/demo/files/sample.irle src/hw20_rle_file_compression/demo/files/sample_restored.txt");
    }
}