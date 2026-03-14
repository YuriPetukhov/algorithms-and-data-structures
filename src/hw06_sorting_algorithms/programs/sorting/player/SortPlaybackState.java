package hw06_sorting_algorithms.programs.sorting.player;

import hw06_sorting_algorithms.visual.ui.status.UiState;

public record SortPlaybackState (
        int[] array,
        Integer highlightIndexA,
        Integer highlightIndexB,
        String lastEvent
) implements UiState {

    @Override
    public String statusLine() {
        int size = array == null ? 0 : array.length;
        String eventText = lastEvent == null ? "" : lastEvent;
        return eventText + " | n=" + size;
    }
}
