package hw21_dynamic_programming.test.config;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public final class FileTestConfigParser {

    public FileTestConfig parse(String[] args) throws IOException {
        Map<String, String> arguments = parseArguments(args);

        String configLocation =
                arguments.getOrDefault(
                        "config",
                        "C:\\Users\\Yuri_P\\IdeaProjects\\algorithms-and-data-structures\\src\\resources\\hw21\\application.properties"
                );

        Properties properties =
                loadProperties(configLocation);

        String taskId = value(arguments, properties, "task", "test.task.id", null);
        String directory = value(arguments, properties, "dir", "test.dir", null);
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing task id. Use --task <task-id> or test.task.id in properties."
            );
        }
        if (directory == null || directory.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing test directory. Use --dir <path> or test.dir in properties."
            );
        }

        String inputExtension = value(
                arguments, properties, "input-ext", "test.input.ext", ".in"
        );
        String outputExtension = value(
                arguments, properties, "output-ext", "test.output.ext", ".out"
        );
        CompareMode compareMode = CompareMode.parse(value(
                arguments, properties, "compare", "test.compare", "trim"
        ));
        int runs = parsePositiveInt(value(
                arguments, properties, "runs", "test.benchmark.runs", "1"
        ), "benchmark runs");
        long timeoutMillis = parseNonNegativeLong(value(
                arguments, properties, "timeout-ms", "test.timeout.millis", "0"
        ), "timeout");
        boolean showPassed = parseBoolean(value(
                arguments, properties, "show-passed", "test.show.passed", "true"
        ));
        boolean showDiff = parseBoolean(value(
                arguments, properties, "show-diff", "test.show.diff", "true"
        ));

        return new FileTestConfig(
                taskId,
                Path.of(directory),
                inputExtension,
                outputExtension,
                compareMode,
                runs,
                timeoutMillis > 0,
                timeoutMillis,
                showPassed,
                showDiff
        );
    }

    public String usage() {
        return """
                Usage:
                  java -cp out hw21_dynamic_programming.test.AlgorithmFileTestApp \\
                    --task <task-id> --dir <tests-directory> [options]

                The directory must contain:
                  inputs/<case>.in
                  outputs/<case>.out

                Options:
                  --input-ext <ext>       default: .in
                  --output-ext <ext>      default: .out
                  --compare exact|trim    default: trim
                  --runs <count>          default: 1
                  --timeout-ms <millis>   default: 0 (disabled)
                  --show-passed true|false
                  --show-diff true|false
                  --config <properties-file>
                """;
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> values = new LinkedHashMap<>();
        if (args == null) {
            return values;
        }
        for (int index = 0; index < args.length; index++) {
            String argument = args[index];
            if ("--help".equals(argument) || "-h".equals(argument)) {
                values.put("help", "true");
                continue;
            }
            if (!argument.startsWith("--")) {
                throw new IllegalArgumentException("Unknown argument: " + argument);
            }
            if (index + 1 >= args.length) {
                throw new IllegalArgumentException("Missing value for argument: " + argument);
            }
            values.put(argument.substring(2), args[++index]);
        }
        return values;
    }

    public boolean helpRequested(String[] args) {
        if (args == null) {
            return false;
        }
        for (String argument : args) {
            if ("--help".equals(argument) || "-h".equals(argument)) {
                return true;
            }
        }
        return false;
    }

    private static Properties loadProperties(String file) throws IOException {
        Properties properties = new Properties();
        if (file == null || file.isBlank()) {
            return properties;
        }
        try (Reader reader = Files.newBufferedReader(Path.of(file), StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
        return properties;
    }

    private static String value(
            Map<String, String> arguments,
            Properties properties,
            String argumentName,
            String propertyName,
            String defaultValue
    ) {
        String argument = arguments.get(argumentName);
        if (argument != null) {
            return argument;
        }
        return properties.getProperty(propertyName, defaultValue);
    }

    private static int parsePositiveInt(String value, String name) {
        try {
            int number = Integer.parseInt(value);
            if (number < 1) {
                throw new IllegalArgumentException(name + " must be positive.");
            }
            return number;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Invalid " + name + ": " + value, exception);
        }
    }

    private static long parseNonNegativeLong(String value, String name) {
        try {
            long number = Long.parseLong(value);
            if (number < 0) {
                throw new IllegalArgumentException(name + " must not be negative.");
            }
            return number;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Invalid " + name + ": " + value, exception);
        }
    }

    private static boolean parseBoolean(String value) {
        if ("true".equalsIgnoreCase(value)) {
            return true;
        }
        if ("false".equalsIgnoreCase(value)) {
            return false;
        }
        throw new IllegalArgumentException("Expected true or false, got: " + value);
    }
}
