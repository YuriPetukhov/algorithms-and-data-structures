package hw21_dynamic_programming.console.model;

import hw21_dynamic_programming.console.model.field.InputField;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class InputForm<I> {

    private final List<String> instructions;
    private final List<InputField<?>> fields;
    private final InputAssembler<I> assembler;

    public InputForm(
            Collection<String> instructions,
            Collection<? extends InputField<?>> fields,
            InputAssembler<I> assembler
    ) {
        Objects.requireNonNull(instructions, "Instructions must not be null.");
        Objects.requireNonNull(fields, "Input fields must not be null.");
        this.instructions = List.copyOf(instructions);
        this.fields = List.copyOf(fields);
        this.assembler = Objects.requireNonNull(
                assembler,
                "Input assembler must not be null."
        );
    }

    public static <I> InputForm<I> of(
            Collection<? extends InputField<?>> fields,
            InputAssembler<I> assembler
    ) {
        return new InputForm<>(List.of(), fields, assembler);
    }

    public static <I> InputForm<I> describedBy(
            Collection<String> instructions,
            Collection<? extends InputField<?>> fields,
            InputAssembler<I> assembler
    ) {
        return new InputForm<>(instructions, fields, assembler);
    }

    public List<String> instructions() {
        return instructions;
    }

    public List<InputField<?>> fields() {
        return fields;
    }

    public I assemble(InputValues values) {
        return assembler.assemble(values);
    }
}
