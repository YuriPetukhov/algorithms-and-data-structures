package hw06_sorting_algorithms.visual.registry;

import hw06_sorting_algorithms.visual.platform.ProgramBuilder;
import hw06_sorting_algorithms.visual.platform.ProgramBundle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

public final class ProgramsBuilder {
    private ProgramsBuilder() {}

    public static List<ProgramBundle<?, ?>> all() {
        List<ProgramBundle<?, ?>> bundles = new ArrayList<>();

        ServiceLoader<ProgramBuilder> loader = ServiceLoader.load(ProgramBuilder.class);
        for (ProgramBuilder builder : loader) {
            bundles.add(builder.build());
        }

        bundles.sort(Comparator.comparing(ProgramBundle::programName));
        return bundles;
    }
}