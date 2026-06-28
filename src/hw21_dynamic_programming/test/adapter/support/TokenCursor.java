package hw21_dynamic_programming.test.adapter.support;

import java.util.ArrayList;
import java.util.List;

public final class TokenCursor {

    private final List<String> tokens;
    private int index;

    public TokenCursor(String rawInput) {
        if (rawInput == null) {
            throw new IllegalArgumentException("Input text must not be null.");
        }
        String trimmed = rawInput.trim();
        this.tokens = new ArrayList<>();
        if (!trimmed.isEmpty()) {
            for (String token : trimmed.split("\\s+")) {
                tokens.add(token);
            }
        }
    }

    public int nextInt(String label) {
        String token = next(label);
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Expected integer for " + label + ", got: " + token,
                    exception
            );
        }
    }

    public String next(String label) {
        if (index >= tokens.size()) {
            throw new IllegalArgumentException(
                    "Missing value for " + label + "."
            );
        }
        return tokens.get(index++);
    }

    public void requireEnd() {
        if (index < tokens.size()) {
            throw new IllegalArgumentException(
                    "Unexpected extra input starting with: " + tokens.get(index)
            );
        }
    }
}
