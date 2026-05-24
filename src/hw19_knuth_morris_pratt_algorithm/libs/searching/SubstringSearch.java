package hw19_knuth_morris_pratt_algorithm.libs.searching;

public interface SubstringSearch {
    SearchResult search(String text, String pattern);

    String name();
}