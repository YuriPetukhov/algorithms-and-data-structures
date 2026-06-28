package hw21_dynamic_programming.test.source;

import hw21_dynamic_programming.test.model.FileTestCase;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class FilePairTestSource {

    private final Path inputsDirectory;
    private final Path outputsDirectory;
    private final String inputExtension;
    private final String outputExtension;

    public FilePairTestSource(
            Path inputsDirectory,
            Path outputsDirectory,
            String inputExtension,
            String outputExtension
    ) {
        this.inputsDirectory = inputsDirectory;
        this.outputsDirectory = outputsDirectory;
        this.inputExtension = inputExtension;
        this.outputExtension = outputExtension;
    }

    public List<FileTestCase> load() throws IOException {
        if (!Files.isDirectory(inputsDirectory)) {
            throw new IOException(
                    "Inputs directory not found: " + inputsDirectory.toAbsolutePath()
            );
        }
        if (!Files.isDirectory(outputsDirectory)) {
            throw new IOException(
                    "Outputs directory not found: " + outputsDirectory.toAbsolutePath()
            );
        }

        List<FileTestCase> testCases = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(
                inputsDirectory,
                "*" + inputExtension
        )) {
            for (Path inputFile : stream) {
                String fileName = inputFile.getFileName().toString();
                String baseName = fileName.substring(
                        0,
                        fileName.length() - inputExtension.length()
                );
                Path outputFile = outputsDirectory.resolve(baseName + outputExtension);
                testCases.add(new FileTestCase(
                        baseName,
                        inputFile,
                        Files.exists(outputFile) ? outputFile : null
                ));
            }
        }

        testCases.sort(Comparator
                .comparingInt((FileTestCase testCase) -> numericIndex(testCase.name()))
                .thenComparing(FileTestCase::name));
        return List.copyOf(testCases);
    }

    private static int numericIndex(String name) {
        StringBuilder digits = new StringBuilder();
        for (int index = 0; index < name.length(); index++) {
            char character = name.charAt(index);
            if (Character.isDigit(character)) {
                digits.append(character);
            } else if (!digits.isEmpty()) {
                break;
            }
        }
        if (digits.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        try {
            return Integer.parseInt(digits.toString());
        } catch (NumberFormatException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}
