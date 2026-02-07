package hw02_dynamic_programming_and_testing.app;

import hw02_dynamic_programming_and_testing.app.bootstrap.DefaultTaskProvider;
import hw02_dynamic_programming_and_testing.app.bootstrap.SelectedTask;
import hw02_dynamic_programming_and_testing.app.bootstrap.TaskProvider;
import hw02_dynamic_programming_and_testing.app.registry.TaskRegistry;
import hw02_dynamic_programming_and_testing.app.render.ResultRenderer;
import hw02_dynamic_programming_and_testing.app.util.ConsoleIO;

public class AlgorithmRunApp {

    public static void main(String[] args) {
        try {
            TaskRegistry registry = new TaskRegistry();
            TaskProvider provider = new DefaultTaskProvider(registry, new ConsoleIO());

            SelectedTask selected = provider.provide(args);
            String result = selected.task().run(selected.input());

            ResultRenderer.render(result);

        } catch (Exception e) {
            ResultRenderer.renderError(e);
        }
    }
}
