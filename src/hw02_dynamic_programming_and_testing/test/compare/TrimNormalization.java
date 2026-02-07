package hw02_dynamic_programming_and_testing.test.compare;

public class TrimNormalization implements TextNormalizationStrategy {
    @Override
    public String normalize(String s) {
        if (s == null) return "";
        return s
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .trim();
    }
}
