package hw02_dynamic_programming_and_testing.test.config;

public interface TestConfigProvider {
    TestConfig provide(String[] args) throws Exception;
}
