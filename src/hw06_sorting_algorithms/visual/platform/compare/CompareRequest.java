package hw06_sorting_algorithms.visual.platform.compare;

import java.util.Set;

public record CompareRequest(Set<String> selectedIds, CompareSettings settings) {
    public CompareRequest {
        if (selectedIds == null || selectedIds.isEmpty())
            throw new IllegalArgumentException("selectedIds is null/empty");
        if (settings == null) throw new IllegalArgumentException("settings is null");
    }
}
