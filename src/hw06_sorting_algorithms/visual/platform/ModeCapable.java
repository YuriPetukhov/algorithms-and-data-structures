package hw06_sorting_algorithms.visual.platform;

import hw06_sorting_algorithms.visual.scene.Scene;

import java.util.List;

public interface ModeCapable {

    List<ModeDescriptor> modes();

    default String defaultModeId() {
        List<ModeDescriptor> ms = modes();
        return (ms == null || ms.isEmpty()) ? "" : ms.get(0).id();
    }

    default boolean isCompareEnabled(String modeId) {
        if (modeId == null) modeId = "";
        for (ModeDescriptor m : modes()) {
            if (m != null && modeId.equals(m.id())) return m.compareEnabled();
        }
        return false;
    }

    Scene<?> sceneForMode(String modeId);

    void applyMode(String modeId);

}