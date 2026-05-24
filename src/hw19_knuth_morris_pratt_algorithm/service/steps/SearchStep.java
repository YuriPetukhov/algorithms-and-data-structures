package hw19_knuth_morris_pratt_algorithm.service.steps;

import hw19_knuth_morris_pratt_algorithm.libs.searching.SearchResult;
import hw19_knuth_morris_pratt_algorithm.service.SearchContext;

public class SearchStep implements Step<SearchContext<SearchResult>> {
    @Override
    public void execute(SearchContext<SearchResult> context) {
        context.setSearchResult(
                context.algorithm().search(
                        context.text(),
                        context.pattern()
                )
        );

        context.setResult(context.searchResult());
    }
}