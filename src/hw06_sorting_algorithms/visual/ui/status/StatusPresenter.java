package hw06_sorting_algorithms.visual.ui.status;

import hw06_sorting_algorithms.visual.platform.Player;

public final class StatusPresenter {

    public String noPlayer() {
        return "No player (press Build)";
    }

    public String format(Player<?> player, Object state) {
        if (player == null) return noPlayer();

        String details = "";

        if (state instanceof UiState uiState) {
            String statusLine = uiState.statusLine();
            if (statusLine != null && !statusLine.isBlank()) {
                details = " | " + statusLine;
            }
        } else if (state != null) {
            details = " | " + state;
        }

        return "Event: " + player.position() + "/" + player.totalEvents() + details;
    }
}