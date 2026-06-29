package hw22_probabilistic_algorithms.dataset;

import hw22_probabilistic_algorithms.dataset.model.DocumentPair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public final class CsvDocumentPairLoader {

    public List<DocumentPair> loadFromResource(
            String resourcePath
    ) {
        Objects.requireNonNull(
                resourcePath,
                "Resource path must not be null."
        );

        InputStream input = getClass()
                .getResourceAsStream(resourcePath);

        if (input == null) {
            throw new IllegalArgumentException(
                    "Dataset resource was not found: "
                            + resourcePath
            );
        }

        List<DocumentPair> pairs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        input,
                        StandardCharsets.UTF_8
                )
        )) {
            read(
                    reader,
                    resourcePath,
                    pairs::add
            );
        } catch (IOException exception) {
            throw new UncheckedIOException(
                    "Failed to read dataset: "
                            + resourcePath,
                    exception
            );
        }

        return List.copyOf(pairs);
    }

    public long forEachFromFile(
            Path path,
            Consumer<DocumentPair> consumer
    ) {
        Objects.requireNonNull(
                path,
                "Path must not be null."
        );

        Objects.requireNonNull(
                consumer,
                "Consumer must not be null."
        );

        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(
                    "Dataset file was not found: "
                            + path.toAbsolutePath()
            );
        }

        try (BufferedReader reader =
                     Files.newBufferedReader(
                             path,
                             StandardCharsets.UTF_8
                     )) {

            return read(
                    reader,
                    path.toAbsolutePath().toString(),
                    consumer
            );
        } catch (IOException exception) {
            throw new UncheckedIOException(
                    "Failed to read dataset: "
                            + path.toAbsolutePath(),
                    exception
            );
        }
    }

    private long read(
            BufferedReader reader,
            String sourceName,
            Consumer<DocumentPair> consumer
    ) throws IOException {

        String headerLine = reader.readLine();

        if (headerLine == null) {
            throw new IllegalArgumentException(
                    "Dataset is empty: " + sourceName
            );
        }

        List<String> header = parseLine(headerLine);
        Map<String, Integer> indexes =
                buildHeaderIndexes(header);

        validateRequiredColumns(indexes);

        String line;
        int lineNumber = 1;
        long processed = 0L;

        while ((line = reader.readLine()) != null) {
            lineNumber++;

            if (line.isBlank()) {
                continue;
            }

            List<String> values = parseLine(line);

            if (values.size() != header.size()) {
                throw new IllegalArgumentException(
                        "Invalid column count at line "
                                + lineNumber
                                + " in "
                                + sourceName
                );
            }

            consumer.accept(
                    toDocumentPair(
                            values,
                            indexes,
                            lineNumber
                    )
            );

            processed++;
        }

        return processed;
    }

    private DocumentPair toDocumentPair(
            List<String> values,
            Map<String, Integer> indexes,
            int lineNumber
    ) {
        String labelValue = value(
                values,
                indexes,
                "label"
        );

        boolean nearDuplicate;

        if ("1".equals(labelValue)) {
            nearDuplicate = true;
        } else if ("0".equals(labelValue)) {
            nearDuplicate = false;
        } else {
            throw new IllegalArgumentException(
                    "Label must be 0 or 1 at line "
                            + lineNumber
            );
        }

        return new DocumentPair(
                value(values, indexes, "pair_id"),
                nearDuplicate,
                value(values, indexes, "pair_type"),
                value(values, indexes, "mutation"),
                value(values, indexes, "first_text"),
                value(values, indexes, "second_text")
        );
    }

    private Map<String, Integer> buildHeaderIndexes(
            List<String> header
    ) {
        Map<String, Integer> indexes =
                new HashMap<>();

        for (int index = 0;
             index < header.size();
             index++) {

            String column = header.get(index);

            if (indexes.put(column, index) != null) {
                throw new IllegalArgumentException(
                        "Duplicate CSV column: "
                                + column
                );
            }
        }

        return indexes;
    }

    private void validateRequiredColumns(
            Map<String, Integer> indexes
    ) {
        List<String> required = List.of(
                "pair_id",
                "label",
                "pair_type",
                "mutation",
                "first_text",
                "second_text"
        );

        for (String column : required) {
            if (!indexes.containsKey(column)) {
                throw new IllegalArgumentException(
                        "Required CSV column is missing: "
                                + column
                );
            }
        }
    }

    private String value(
            List<String> values,
            Map<String, Integer> indexes,
            String column
    ) {
        return values.get(indexes.get(column));
    }

    private List<String> parseLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean quoted = false;

        for (int index = 0;
             index < line.length();
             index++) {

            char character = line.charAt(index);

            if (character == '"') {
                if (quoted
                        && index + 1 < line.length()
                        && line.charAt(index + 1) == '"') {

                    current.append('"');
                    index++;
                } else {
                    quoted = !quoted;
                }
            } else if (character == ','
                    && !quoted) {

                values.add(current.toString());
                current.setLength(0);
            } else {
                current.append(character);
            }
        }

        if (quoted) {
            throw new IllegalArgumentException(
                    "Unclosed quoted CSV value."
            );
        }

        values.add(current.toString());
        return values;
    }
}
