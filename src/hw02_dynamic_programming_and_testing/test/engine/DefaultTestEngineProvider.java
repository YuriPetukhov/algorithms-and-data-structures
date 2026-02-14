package hw02_dynamic_programming_and_testing.test.engine;

import hw02_dynamic_programming_and_testing.app.registry.TaskRegistry;

public class DefaultTestEngineProvider implements TestEngineProvider {
    @Override
    public TestEngine get() {
        return new DefaultTestEngine(new TaskRegistry());
    }
}
