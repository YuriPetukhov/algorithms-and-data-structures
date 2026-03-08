package hw08_quick_and_merge_sort.programs.external_sorting.fs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class WorkDirManager {

    private final Path baseDir;

    public WorkDirManager(Path baseDir) {
        this.baseDir = baseDir;
    }

    public ExternalSortPaths createRun() throws IOException {

        Files.createDirectories(baseDir);

        String runName = "run_" + System.currentTimeMillis();
        Path workDir = baseDir.resolve(runName);

        Files.createDirectories(workDir);

        Path inputFile = workDir.resolve("input.txt");
        Path outputFile = workDir.resolve("output.txt");

        return new ExternalSortPaths(
                workDir,
                inputFile,
                outputFile
        );
    }
}