package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

public interface InputField<T> {

    InputKey<T> key();

    T read(
            ConsoleInput input,
            ConsoleOutput output,
            InputValues previousValues
    );
}
