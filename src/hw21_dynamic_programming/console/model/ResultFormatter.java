package hw21_dynamic_programming.console.model;

@FunctionalInterface
public interface ResultFormatter<O> {

    String format(O result);
}
