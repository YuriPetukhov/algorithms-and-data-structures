package hw08_quick_and_merge_sort.programs.external_sorting.context;

import hw08_quick_and_merge_sort.programs.external_sorting.config.ExternalSortingConfig;
import hw08_quick_and_merge_sort.programs.external_sorting.input.ExternalSortingRequest;
import hw08_quick_and_merge_sort.programs.external_sorting.runner.ExternalSortRunResult;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingParams;

public final class ExternalSortingContext {

    private final String variantId;
    private final String rawInput;

    private ExternalSortingConfig config;
    private ExternalSortingRequest request;
    private ExternalSortingParams params;
    private ExternalSortRunResult runResult;
    private String result;

    public ExternalSortingContext(String variantId, String rawInput) {
        this.variantId = variantId;
        this.rawInput = rawInput;
    }

    public String variantId() {
        return variantId;
    }

    public String rawInput() {
        return rawInput;
    }

    public ExternalSortingConfig config() {
        return config;
    }
    public ExternalSortingRequest request() {
        return request;
    }
    public void setConfig(ExternalSortingConfig config) {
        this.config = config;
    }
    public void setRequest(ExternalSortingRequest request) {
        this.request = request;
    }
    public ExternalSortingParams params() {
        return params;
    }
    public void setParams(ExternalSortingParams params) {
        this.params = params;
    }

    public ExternalSortRunResult runResult() {
        return runResult;
    }

    public void setRunResult(ExternalSortRunResult runResult) {
        this.runResult = runResult;
    }

    public String result() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}