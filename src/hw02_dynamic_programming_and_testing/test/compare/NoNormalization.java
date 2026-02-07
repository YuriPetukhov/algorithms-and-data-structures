package hw02_dynamic_programming_and_testing.test.compare;

public class NoNormalization implements TextNormalizationStrategy {
    @Override
    public String normalize(String s) {
        return s == null ? "" : s.replace("\r\n", "\n").replace("\r", "\n");
    }
}
