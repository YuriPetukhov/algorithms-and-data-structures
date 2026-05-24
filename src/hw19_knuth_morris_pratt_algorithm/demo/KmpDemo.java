package hw19_knuth_morris_pratt_algorithm.demo;

import hw19_knuth_morris_pratt_algorithm.libs.searching.KnuthMorrisPrattSearch;
import hw19_knuth_morris_pratt_algorithm.libs.searching.SearchResult;
import hw19_knuth_morris_pratt_algorithm.libs.searching.SubstringSearch;
import hw19_knuth_morris_pratt_algorithm.service.AlgorithmService;
import hw19_knuth_morris_pratt_algorithm.service.Handler;
import hw19_knuth_morris_pratt_algorithm.service.SearchContext;
import hw19_knuth_morris_pratt_algorithm.service.steps.SearchStep;
import hw19_knuth_morris_pratt_algorithm.service.steps.ValidateSearchInputStep;
import hw19_knuth_morris_pratt_algorithm.visualization.SearchConsoleVisualizer;

import java.util.List;

public class KmpDemo {
    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "ababd";
        SubstringSearch algorithm = new KnuthMorrisPrattSearch();

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

        SearchConsoleVisualizer.title("Knuth-Morris-Pratt search demo");
        SearchConsoleVisualizer.input(text, pattern, algorithm.name());

        SearchConsoleVisualizer.emptyLine();

        SearchConsoleVisualizer.title("Search result");
        SearchConsoleVisualizer.result(result);
    }
}