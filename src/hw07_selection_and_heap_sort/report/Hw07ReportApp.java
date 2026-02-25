package hw07_selection_and_heap_sort.report;

import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.report.config.ReportConfig;
import hw02_dynamic_programming_and_testing.report.io.TableConsolePrinter;
import hw02_dynamic_programming_and_testing.report.model.ReportTable;
import hw02_dynamic_programming_and_testing.report.service.ReportRunner;
import hw02_dynamic_programming_and_testing.test.config.ArgsOverrideProvider;
import hw02_dynamic_programming_and_testing.test.config.DefaultTestConfigProvider;
import hw02_dynamic_programming_and_testing.test.config.PropertiesTestConfigProvider;
import hw02_dynamic_programming_and_testing.test.config.TestConfigProvider;
import hw02_dynamic_programming_and_testing.test.engine.DefaultTestEngineProvider;
import hw02_dynamic_programming_and_testing.test.engine.TestEngine;

public final class Hw07ReportApp {

    public static void main(String[] args) {
        try {
            PropertiesLoader propertiesLoader = new PropertiesLoader();

            TestConfigProvider configProvider =
                    new DefaultTestConfigProvider(
                            new PropertiesTestConfigProvider(
                                    propertiesLoader,
                                    "hw07/application.properties"
                            ),
                            new ArgsOverrideProvider()
                    );

            TestEngine engine = new DefaultTestEngineProvider().get();

            var reportProperties = propertiesLoader.loadFromClasspath("hw07/report.properties");
            ReportConfig reportConfig = ReportConfig.fromProperties(reportProperties);

            ReportRunner reportRunner = new ReportRunner(engine, configProvider);
            TableConsolePrinter printer = new TableConsolePrinter();

            for (ReportConfig.Suite suite : reportConfig.suites()) {
                ReportTable table = reportRunner.runByInputTable(suite);
                printer.print(table);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}