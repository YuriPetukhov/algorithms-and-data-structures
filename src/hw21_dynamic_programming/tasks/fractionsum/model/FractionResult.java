package hw21_dynamic_programming.tasks.fractionsum.model;

public record FractionResult(long numerator, long denominator) {

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
