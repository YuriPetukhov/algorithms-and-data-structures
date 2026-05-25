package hw20_rle_file_compression.cli;

import hw20_rle_file_compression.libs.compression.Compressor;
import hw20_rle_file_compression.libs.compression.ImprovedRleCompressor;
import hw20_rle_file_compression.libs.compression.RleCompressor;

import java.nio.file.Path;

public class CommandParser {
    public Command parse(String[] args) {
        if (args == null || args.length != 4) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        boolean compress = parseOperation(args[0]);
        Compressor compressor = parseCompressor(args[1]);
        Path inputPath = Path.of(args[2]);
        Path outputPath = Path.of(args[3]);

        return new Command(
                compress,
                compressor,
                inputPath,
                outputPath
        );
    }

    private boolean parseOperation(String value) {
        if ("compress".equalsIgnoreCase(value)) {
            return true;
        }

        if ("decompress".equalsIgnoreCase(value)) {
            return false;
        }

        throw new IllegalArgumentException("Unknown operation: " + value);
    }

    private Compressor parseCompressor(String value) {
        if ("simple".equalsIgnoreCase(value)) {
            return new RleCompressor();
        }

        if ("improved".equalsIgnoreCase(value)) {
            return new ImprovedRleCompressor();
        }

        throw new IllegalArgumentException("Unknown algorithm: " + value);
    }
}