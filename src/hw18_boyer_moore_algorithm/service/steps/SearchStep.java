package hw18_boyer_moore_algorithm.service.steps;

import hw18_boyer_moore_algorithm.libs.searching.SearchResult;
import hw18_boyer_moore_algorithm.service.SearchContext;

public class SearchStep implements Step<SearchContext<SearchResult>> {
    @Override
    public void execute(SearchContext<SearchResult> context) {
        context.setSearchResult(
                context.algorithm().search(context.text(), context.pattern())
        );

        context.setResult(context.searchResult());
    }
}