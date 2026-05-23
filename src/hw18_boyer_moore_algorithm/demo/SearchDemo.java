package hw18_boyer_moore_algorithm.demo;

import hw18_boyer_moore_algorithm.libs.searching.BoyerMooreSearch;
import hw18_boyer_moore_algorithm.libs.searching.SearchResult;
import hw18_boyer_moore_algorithm.libs.searching.SubstringSearch;
import hw18_boyer_moore_algorithm.service.AlgorithmService;
import hw18_boyer_moore_algorithm.service.Handler;
import hw18_boyer_moore_algorithm.service.SearchContext;
import hw18_boyer_moore_algorithm.service.steps.SearchStep;
import hw18_boyer_moore_algorithm.service.steps.ValidateSearchInputStep;
import hw18_boyer_moore_algorithm.visualization.SearchConsoleVisualizer;

import java.util.List;

public class SearchDemo {
    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "ababd";
        SubstringSearch algorithm = new BoyerMooreSearch();

        AlgorithmService<SearchContext<SearchResult>, SearchResult> service =
                new AlgorithmService<>(
                        new Handler<>(
                                List.of(
                                        new ValidateSearchInputStep<>(),
                                        new SearchStep()
                                )
                        )
                );

        SearchResult result = service.execute(
                new SearchContext<>(
                        text,
                        pattern,
                        algorithm
                )
        );

        SearchConsoleVisualizer.title("Substring search demo");
        SearchConsoleVisualizer.input(text, pattern, algorithm.name());

        SearchConsoleVisualizer.emptyLine();

        SearchConsoleVisualizer.title("Search result");
        SearchConsoleVisualizer.result(result);
    }
}