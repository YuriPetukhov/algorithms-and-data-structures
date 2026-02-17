package hw05_bitboards_and_chess_moves.report;

import hw02_dynamic_programming_and_testing.common.props.PropertiesLoader;
import hw02_dynamic_programming_and_testing.report.config.ReportConfig;
import hw02_dynamic_programming_and_testing.report.io.TableConsolePrinter;
import hw02_dynamic_programming_and_testing.report.model.ReportTable;
import hw02_dynamic_programming_and_testing.report.service.ReportRunner;
import hw02_dynamic_programming_and_testing.test.config.*;
import hw02_dynamic_programming_and_testing.test.engine.DefaultTestEngineProvider;
import hw02_dynamic_programming_and_testing.test.engine.TestEngine;

public class Hw05ReportApp {
    public static void main(String[] args) {
        try {
            TestConfigProvider configProvider =
                    new DefaultTestConfigProvider(
                            new PropertiesTestConfigProvider(
                                    new PropertiesLoader(),
                                    "hw02/application.properties"
                            ),
                            new ArgsOverrideProvider()
                    );

            TestEngine engine = new DefaultTestEngineProvider().get();

            PropertiesLoader loader = new PropertiesLoader();
            var props = loader.loadFromClasspath("hw05/report.properties");
            ReportConfig reportCfg = ReportConfig.fromProperties(props);

            ReportRunner runner = new ReportRunner(engine, configProvider);
            TableConsolePrinter printer = new TableConsolePrinter();

            for (ReportConfig.Suite suite : reportCfg.suites()) {
                System.out.println();
                System.out.println();
                ReportTable table = runner.runByInputTable(suite);
                printer.print(table);
                System.out.println("=================================================");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
