package hw06_sorting_algorithms.visual.ui.compare;

import hw06_sorting_algorithms.visual.platform.compare.CompareCapable;
import hw06_sorting_algorithms.visual.platform.compare.CompareReport;
import hw06_sorting_algorithms.visual.platform.compare.CompareRequest;

import javax.swing.*;
import java.util.function.Consumer;

public final class CompareRunner {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void runAsync(CompareCapable cap,
                         Object lockedInput,
                         CompareRequest request,
                         Consumer<CompareReport> onOk,
                         Consumer<Exception> onErr) {

        new SwingWorker<CompareReport, Void>() {
            @Override
            protected CompareReport doInBackground() {
                return cap.compare(lockedInput, request);
            }

            @Override
            protected void done() {
                try {
                    onOk.accept(get());
                } catch (Exception ex) {
                    onErr.accept(ex);
                }
            }
        }.execute();
    }
}