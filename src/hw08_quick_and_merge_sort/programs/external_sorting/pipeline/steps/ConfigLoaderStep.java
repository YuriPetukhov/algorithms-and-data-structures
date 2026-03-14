package hw08_quick_and_merge_sort.programs.external_sorting.pipeline.steps;

import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.common.props.Props;
import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;
import hw08_quick_and_merge_sort.programs.external_sorting.config.ExternalSortingConfig;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStep;

import java.nio.file.Path;
import java.util.Properties;

public class ConfigLoaderStep implements ExternalSortingStep {

    private static final PropertiesLoader loader = new PropertiesLoader();

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void execute(ExternalSortingContext context) {

        try {
            Properties props =
                    loader.loadFromClasspath("hw08/external_sorting.properties");

            String workDir = Props.get(
                    props,
                    "external.sort.workDir",
                    "tests/hw08"
            );

            int blockSize = Props.getInt(
                    props,
                    "external.sort.blockSize",
                    100
            );

            context.setConfig(
                    new ExternalSortingConfig(
                            Path.of(workDir),
                            blockSize
                    )
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load external programs configuration",
                    e
            );
        }
    }
}