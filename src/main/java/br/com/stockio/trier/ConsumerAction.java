package br.com.stockio.trier;

import java.util.function.Consumer;

public class ConsumerAction<I> implements Action<I, Void>{

    private final Consumer<I> consumer;

    ConsumerAction(Consumer<I> consumer) {
        this.consumer = consumer;
    }

    @Override
    public Void execute(I input) {
        this.consumer.accept(input);
        return null;
    }
}
