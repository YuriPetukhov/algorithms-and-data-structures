package hw19_knuth_morris_pratt_algorithm.service;

import hw19_knuth_morris_pratt_algorithm.libs.searching.SearchResult;
import hw19_knuth_morris_pratt_algorithm.libs.searching.SubstringSearch;

public class SearchContext<R> implements AlgorithmContext<R> {
    private final String text;
    private final String pattern;
    private final SubstringSearch algorithm;

    private SearchResult searchResult;
    private R result;

    public SearchContext(
            String text,
            String pattern,
            SubstringSearch algorithm
    ) {
        this.text = text;
        this.pattern = pattern;
        this.algorithm = algorithm;
    }

    public String text() {
        return text;
    }

    public String pattern() {
        return pattern;
    }

    public SubstringSearch algorithm() {
        return algorithm;
    }

    public SearchResult searchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    @Override
    public R result() {
        return result;
    }

    @Override
    public void setResult(R result) {
        this.result = result;
    }
}