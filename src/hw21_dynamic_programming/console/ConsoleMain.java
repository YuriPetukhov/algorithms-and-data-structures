package hw21_dynamic_programming.console;

import hw21_dynamic_programming.console.bootstrap.ConsoleLauncher;

public final class ConsoleMain {

    private ConsoleMain() {
    }

    public static void main(String[] args) {
        ConsoleLauncher
                .usingServiceLoaders()
                .run(System.in, System.out);
    }
}
