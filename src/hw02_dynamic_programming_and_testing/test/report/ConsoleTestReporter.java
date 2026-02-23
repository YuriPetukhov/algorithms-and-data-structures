package hw02_dynamic_programming_and_testing.test.report;

import hw02_dynamic_programming_and_testing.test.model.TestResult;
import hw02_dynamic_programming_and_testing.test.model.TestStatus;

import java.util.List;

public class ConsoleTestReporter {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String MAGENTA = "\u001B[35m";

    public void print(List<TestResult> results) {
        int passed = 0, timeout = 0, failed = 0, missing = 0, error = 0;

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

            System.out.println(color(r.status(), line));

            if (r.status() == TestStatus.FAILED) {
                System.out.println("  expected: " + smartPreview(r.expected()));
                System.out.println("  actual  : " + smartPreview(r.actual()));
            } else if (r.status() == TestStatus.ERROR || r.status() == TestStatus.MISSING_EXPECTED) {
                System.out.println("  message : " + r.message());
            } else if (r.status() == TestStatus.TIMEOUT) {
                System.out.println("  message : " + r.message());
            }
        }

        System.out.println("--------------------------------");
        System.out.printf("Total: %d, Passed: %d, Timeout: %d, Failed: %d, Missing: %d, Error: %d%n",
                results.size(), passed, timeout, failed, missing, error);
    }

    public void printFatal(Exception e) {
        System.err.println("Ошибка: " + e.getMessage());
    }

    private String color(TestStatus status, String s) {
        return switch (status) {
            case PASSED -> GREEN + s + RESET;
            case FAILED, ERROR -> RED + s + RESET;
            case MISSING_EXPECTED -> YELLOW + s + RESET;
            case TIMEOUT -> MAGENTA + s + RESET;
        };
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