package hw06_sorting_algorithms.visual.platform;

public record ModeDescriptor(
        String id,
        String displayName,
        boolean compareEnabled
) {
    @Override
    public String toString() {
        return displayName;
    }
}
