package hw08_quick_and_merge_sort.test.report;

import hw02_dynamic_programming_and_testing.test.model.TestResult;
import hw02_dynamic_programming_and_testing.test.model.TestStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileTestReporter implements TestReporter, AutoCloseable {

    private final PrintWriter out;

    public FileTestReporter(Path file) throws IOException {
        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }
        this.out = new PrintWriter(Files.newBufferedWriter(file));
    }

    public void printSection(String testDir, String algorithmId, String algorithmName) {
        out.println();
        out.println("========================================");
        out.println("TEST DIR : " + testDir);
        out.println("ALGORITHM: " + algorithmId);
        out.println("NAME     : " + algorithmName);
        out.println("========================================");
        out.flush();
    }

    public void println(String line) {
        out.println(line);
        out.flush();
    }

    @Override
    public void print(List<TestResult> results) {
        int passed = 0;
        int timeout = 0;
        int failed = 0;
        int missing = 0;
        int error = 0;

        for (TestResult r : results) {
            switch (r.status()) {
                case PASSED -> passed++;
                case TIMEOUT -> timeout++;
                case FAILED -> failed++;
                case MISSING_EXPECTED -> missing++;
                case ERROR -> error++;
            }

            String line = String.format("[%s] %s | %.3f ms",
                    r.status(), r.testName(), r.timeNanos() / 1_000_000.0);

            out.println(line);

            if (r.status() == TestStatus.FAILED) {
                out.println("  expected: " + smartPreview(r.expected()));
                out.println("  actual  : " + smartPreview(r.actual()));
            } else if (r.status() == TestStatus.ERROR || r.status() == TestStatus.MISSING_EXPECTED) {
                out.println("  message : " + r.message());
            } else if (r.status() == TestStatus.TIMEOUT) {
                out.println("  message : " + r.message());
            }
        }

        out.println("--------------------------------");
        out.printf(
                "Total: %d, Passed: %d, Timeout: %d, Failed: %d, Missing: %d, Error: %d%n",
                results.size(), passed, timeout, failed, missing, error
        );
        out.flush();
    }

    @Override
    public void printFatal(Exception e) {
        out.println("Ошибка: " + e.getMessage());
        out.flush();
    }

    @Override
    public void close() {
        out.close();
    }

    private static String smartPreview(String s) {
        if (looksLikeNumberSequence(s)) {
            return previewHeadTail(s, 5, 5);
        }
        return preview(s, 120);
    }

    private static boolean looksLikeNumberSequence(String s) {
        if (s == null) return false;

        String t = s.trim();
        if (t.isEmpty()) return false;

        String[] parts = t.split("\\s+");
        if (parts.length < 5) return false;

        int check = Math.min(parts.length, 10);
        for (int i = 0; i < check; i++) {
            if (!isInteger(parts[i])) return false;
        }

        return true;
    }

    private static String previewHeadTail(String s, int head, int tail) {
        if (s == null) return "null";

        String[] parts = s.trim().split("\\s+");

        if (parts.length <= head + tail) {
            return String.join(" ", parts) + " (size=" + parts.length + ")";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < head; i++) {
            sb.append(parts[i]).append(' ');
        }

        sb.append("... ");

        for (int i = parts.length - tail; i < parts.length; i++) {
            sb.append(parts[i]).append(' ');
        }

        sb.append("(size=").append(parts.length).append(')');

        return sb.toString();
    }

    private static boolean isInteger(String x) {
        if (x == null || x.isEmpty()) return false;

        int i = (x.charAt(0) == '-') ? 1 : 0;
        if (i == x.length()) return false;

        for (; i < x.length(); i++) {
            char c = x.charAt(i);
            if (c < '0' || c > '9') return false;
        }

        return true;
    }

    private static String preview(String s, int limit) {
        if (s == null) return "null";

        String trimmed = s.trim();

        if (trimmed.length() <= limit) {
            return trimmed + " (len=" + trimmed.length() + ")";
        }

        return trimmed.substring(0, limit) + "... (len=" + trimmed.length() + ")";
    }
}