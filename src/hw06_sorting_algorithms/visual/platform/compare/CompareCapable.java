package hw06_sorting_algorithms.visual.platform.compare;

import hw06_sorting_algorithms.visual.platform.AlgorithmVariant;

import java.util.List;

public interface CompareCapable<I> {
    List<AlgorithmVariant<I>> variants();

    default CompareSettings defaultSettings() {
        return new CompareSettings(1, 5);
    }

    CompareReport compare(I lockedInput, CompareRequest request);

    default String inputLabel(I lockedInput) {
        return lockedInput == null ? "" : lockedInput.toString();
    }
}