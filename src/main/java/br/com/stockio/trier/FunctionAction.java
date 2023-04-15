package br.com.stockio.trier;

import java.util.function.Function;

public class FunctionAction<I, O> implements Action<I, O> {

    private final Function<I, O> function;

    FunctionAction(Function<I, O> function) {
        this.function = function;
    }

    @Override
    public O execute(I input) {
        return this.function.apply(input);
    }
}
