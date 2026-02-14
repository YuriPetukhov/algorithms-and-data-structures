package hw02_dynamic_programming_and_testing.report.service;

import hw02_dynamic_programming_and_testing.report.config.ReportConfig;
import hw02_dynamic_programming_and_testing.report.model.ReportTable;
import hw02_dynamic_programming_and_testing.report.mapper.DefaultCellMapper;
import hw02_dynamic_programming_and_testing.report.transform.RowKeyFormatRegistry;
import hw02_dynamic_programming_and_testing.report.transform.RowKeyFormat;
import hw02_dynamic_programming_and_testing.report.transform.FormatRowKeyResolver;
import hw02_dynamic_programming_and_testing.report.transform.ReportTableBuilder;
import hw02_dynamic_programming_and_testing.test.config.TestConfig;
import hw02_dynamic_programming_and_testing.test.config.TestConfigProvider;
import hw02_dynamic_programming_and_testing.test.engine.TestEngine;
import hw02_dynamic_programming_and_testing.test.engine.TestRunResult;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportRunner {

    private final TestEngine engine;
    private final TestConfigProvider configProvider;

    public ReportRunner(
            TestEngine engine,
            TestConfigProvider configProvider
    ) {
        this.engine = engine;
        this.configProvider = configProvider;
    }

    public ReportTable runByInputTable(ReportConfig.Suite suite) throws Exception {

        Map<String, List<TestResult>> resultsByTask = new LinkedHashMap<>();

        int runs = (suite.runs() == null ? 1 : suite.runs());

        Path inputsDir = null;
        String inputExt = null;

        for (String taskId : suite.taskIds()) {
            TestConfig cfg = configProvider.provide(new String[]{
                    "--task", taskId,
                    "--dir", suite.testDir().toString()
            });

            if (inputsDir == null) {
                inputsDir = cfg.inputsDir();
                inputExt = cfg.inputExt();
            }

            TestRunResult run = engine.run(cfg, runs);
            resultsByTask.put(taskId, run.results());
        }

        RowKeyFormatRegistry registry = new RowKeyFormatRegistry();
        RowKeyFormat format = registry.get(suite.rowKeyFormatId());
        FormatRowKeyResolver resolver = new FormatRowKeyResolver(format);

        ReportTableBuilder builder = new ReportTableBuilder();
        return builder.build(
                suite.name(),
                inputsDir,
                inputExt,
                suite.taskIds(),
                resultsByTask,
                resolver,
                new DefaultCellMapper(),
                runs,
                suite.timeUnit(),
                suite.aggregation()
        );
    }
}
