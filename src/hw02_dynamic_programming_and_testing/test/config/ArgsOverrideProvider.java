package hw02_dynamic_programming_and_testing.test.config;

import java.nio.file.Path;

public class ArgsOverrideProvider {

    public ArgsOverride read(String[] args) {
        String task = getArg(args, "--task");
        String dir = getArg(args, "--dir");

        Path testDir = (dir == null || dir.isBlank()) ? null : Path.of(dir);

        return new ArgsOverride(
                (task == null || task.isBlank()) ? null : task,
                testDir
        );
    }

    private String getArg(String[] args, String name) {
        if (args == null) return null;
        for (int i = 0; i < args.length - 1; i++) {
            if (name.equals(args[i])) return args[i + 1];
        }
        return null;
    }
}

