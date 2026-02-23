package hw06_sorting_algorithms.visual.engine;

import hw06_sorting_algorithms.visual.platform.Player;

import javax.swing.*;
import java.util.Objects;
import java.util.function.IntSupplier;

public final class PlaybackEngine {

    public interface Listener {
        void onNoPlayer();
        void onState(Object state);
        void onDone();
    }

    private final Listener listener;
    private final Timer timer;
    private final IntSupplier delayMillisSupplier;

    private Player<?> player;

    public PlaybackEngine(IntSupplier delayMillisSupplier, Listener listener) {
        this.delayMillisSupplier = Objects.requireNonNull(delayMillisSupplier, "delayMillisSupplier is null");
        this.listener = Objects.requireNonNull(listener, "listener is null");
        this.timer = new Timer(Math.max(1, delayMillisSupplier.getAsInt()), e -> onTimerTick());
        this.timer.setRepeats(true);
        this.timer.setCoalesce(true);
    }

    public Player<?> player() {
        return player;
    }

    public boolean isRunning() {
        return timer.isRunning();
    }

    public void setPlayer(Player<?> newPlayer) {
        stopTimer();
        this.player = newPlayer;

        if (player == null) {
            listener.onNoPlayer();
            return;
        }

        listener.onState(player.state());

        if (!player.hasNext()) {
            listener.onDone();
        }
    }

    public void updateDelay() {
        timer.setDelay(Math.max(1, delayMillisSupplier.getAsInt()));
    }

    public void play() {
        if (player == null) {
            listener.onNoPlayer();
            return;
        }

        if (!player.hasNext()) {
            listener.onDone();
            return;
        }

        if (isRunning()) {
            return;
        }

        updateDelay();
        timer.start();
    }

    public void pause() {
        stopTimer();
    }

    public void stop() {
        stopTimer();
    }

    public void reset() {
        if (player == null) {
            listener.onNoPlayer();
            return;
        }

        stopTimer();
        player.reset();

        listener.onState(player.state());
        if (!player.hasNext()) {
            listener.onDone();
        }
    }

    public void stepOnce() {
        if (player == null) {
            listener.onNoPlayer();
            return;
        }

        stopTimer();
        advanceOneStep();
    }

    private void onTimerTick() {
        if (player == null) {
            stopTimer();
            listener.onNoPlayer();
            return;
        }

        if (!player.hasNext()) {
            stopTimer();
            listener.onState(player.state());
            listener.onDone();
            return;
        }

        advanceOneStep();
    }

    private void advanceOneStep() {
        if (!player.hasNext()) {
            listener.onState(player.state());
            listener.onDone();
            return;
        }

        Object newState = player.step();
        listener.onState(newState);

        if (!player.hasNext()) {
            stopTimer();
            listener.onDone();
        }
    }

    private void stopTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }
}