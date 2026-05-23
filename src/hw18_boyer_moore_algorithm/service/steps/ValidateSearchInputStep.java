package hw18_boyer_moore_algorithm.service.steps;

import hw18_boyer_moore_algorithm.service.SearchContext;

public class ValidateSearchInputStep<R> implements Step<SearchContext<R>> {
    @Override
    public void execute(SearchContext<R> context) {
        if (context.text() == null) {
            throw new IllegalArgumentException("Text must not be null");
        }

        if (context.pattern() == null) {
            throw new IllegalArgumentException("Pattern must not be null");
        }

        if (context.pattern().isEmpty()) {
            throw new IllegalArgumentException("Pattern must not be empty");
        }

        if (context.algorithm() == null) {
            throw new IllegalArgumentException("Search algorithm must not be null");
        }

        if (context.pattern().length() > context.text().length()) {
            throw new IllegalArgumentException("Pattern length must not be greater than text length");
        }
    }
}