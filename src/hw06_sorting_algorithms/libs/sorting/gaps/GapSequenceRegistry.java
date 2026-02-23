package hw06_sorting_algorithms.libs.sorting.gaps;

import java.util.*;

public final class GapSequenceRegistry {

    private static final GapSequenceRegistry INSTANCE = new GapSequenceRegistry();

    private final List<GapSequence> sequences;

    private GapSequenceRegistry() {
        List<GapSequence> list = new ArrayList<>();
        ServiceLoader.load(GapSequence.class).forEach(list::add);

        if (list.isEmpty()) {
            throw new IllegalStateException("No GapSequence implementations found via ServiceLoader");
        }
        sequences = List.copyOf(list);
    }

    public static GapSequenceRegistry get() {
        return INSTANCE;
    }

    public List<GapSequence> all() {
        return sequences;
    }
}