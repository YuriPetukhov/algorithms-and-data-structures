package hw21_dynamic_programming.console.model;

@FunctionalInterface
public interface InputAssembler<I> {

    I assemble(InputValues values);
}
