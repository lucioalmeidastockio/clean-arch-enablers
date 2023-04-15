package br.com.stockio.trier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

interface ActionFactory {

    static <I, O> Action<I, O> of(Function<I, O> function){
        return new FunctionAction<>(function);
    }

    static <I> Action<I, Void> of(Consumer<I> consumer){
        return new ConsumerAction<>(consumer);
    }

    static <O> Action<Void, O> of(Supplier<O> supplier){
        return new SupplierAction<>(supplier);
    }

    static Action<Void, Void> of(Runnable runnable){
        return new RunnableAction(runnable);
    }

}
