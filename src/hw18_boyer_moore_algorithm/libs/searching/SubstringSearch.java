package hw18_boyer_moore_algorithm.libs.searching;

public interface SubstringSearch {
    SearchResult search(String text, String pattern);

    String name();
}