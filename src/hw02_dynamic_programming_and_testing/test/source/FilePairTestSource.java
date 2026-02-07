package hw02_dynamic_programming_and_testing.test.source;

import hw02_dynamic_programming_and_testing.test.model.TestCase;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilePairTestSource {

    private final Path inputsDir;
    private final Path outputsDir;
    private final String inputExt;
    private final String outputExt;

    public FilePairTestSource(Path inputsDir, Path outputsDir, String inputExt, String outputExt) {
        this.inputsDir = inputsDir;
        this.outputsDir = outputsDir;
        this.inputExt = normalizeExt(inputExt);
        this.outputExt = normalizeExt(outputExt);
    }

    public List<TestCase> load() throws IOException {
        if (!Files.isDirectory(inputsDir)) {
            throw new IOException("Inputs dir not found: " + inputsDir.toAbsolutePath());
        }
        if (!Files.isDirectory(outputsDir)) {
            throw new IOException("Outputs dir not found: " + outputsDir.toAbsolutePath());
        }

        List<TestCase> cases = new ArrayList<>();
        String glob = "*" + inputExt;

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(inputsDir, glob)) {
            for (Path in : ds) {
                String fileName = in.getFileName().toString();
                String base = stripExtension(fileName, inputExt);

                Path out = outputsDir.resolve(base + outputExt);

                cases.add(new TestCase(
                        base,
                        in,
                        Files.exists(out) ? out : null
                ));
            }
        }

        cases.sort(Comparator.comparingInt(FilePairTestSource::extractIndex));
        return cases;
    }

    private static String normalizeExt(String ext) {
        if (ext == null) return "";
        String e = ext.trim();
        if (e.isEmpty()) return "";
        return e.startsWith(".") ? e : "." + e;
    }

    private static String stripExtension(String fileName, String ext) {
        if (!ext.isEmpty() && fileName.endsWith(ext)) {
            return fileName.substring(0, fileName.length() - ext.length());
        }
        return fileName;
    }

    private static int extractIndex(TestCase tc) {
        return extractIndex(tc.name());
    }

    private static int extractIndex(String name) {
        int dot = name.lastIndexOf('.');
        String tail = (dot >= 0) ? name.substring(dot + 1) : name;

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < tail.length(); i++) {
            char c = tail.charAt(i);
            if (c >= '0' && c <= '9') digits.append(c);
            else break;
        }

        if (digits.isEmpty()) return Integer.MAX_VALUE;
        return Integer.parseInt(digits.toString());
    }
}
