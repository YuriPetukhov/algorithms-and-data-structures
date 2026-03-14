package hw09_linear_sorting.programs.distribution_sorting.spi;

public record DistributionSortingParams(
        int minValue,
        int maxValue
) {

    public static DistributionSortingParams defaults() {
        return new DistributionSortingParams(0, 999);
    }
}
