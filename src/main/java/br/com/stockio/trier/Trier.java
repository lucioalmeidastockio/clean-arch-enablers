package br.com.stockio.trier;

import br.com.stockio.mapped_exceptions.MappedException;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Trier<I, O> {

    private final Action<I, O> action;
    private final I input;
    private final UnexpectedExceptionHandler unexpectedExceptionHandler;

    private Trier(Action<I, O> action, I input, UnexpectedExceptionHandler unexpectedExceptionHandler) {
        this.action = action;
        this.input = input;
        this.unexpectedExceptionHandler = unexpectedExceptionHandler;
    }

    public static <I, O> TrierBuilder<I, O> of(Function<I, O> function, I input){
        return new TrierBuilder<>(ActionFactory.of(function), input);
    }

    public static <I> TrierBuilder<I, Void> of(Consumer<I> consumer, I input){
        return new TrierBuilder<>(ActionFactory.of(consumer), input);
    }

    public static <O> TrierBuilder<Void, O> of(Supplier<O> supplier){
        return new TrierBuilder<>(ActionFactory.of(supplier), null);
    }

    public static TrierBuilder<Void, Void> of(Runnable runnable){
        return new TrierBuilder<>(ActionFactory.of(runnable), null);
    }

    public O andExecuteTheAction(){
        try {
            return this.action.execute(this.input);
        } catch (MappedException mappedException){
            throw mappedException;
        } catch (Exception unexpectedException){
            throw this.unexpectedExceptionHandler.handle(unexpectedException);
        }
    }

    public static class TrierBuilder<I, O>{

        private final Action<I, O> action;
        private final I input;

        private TrierBuilder(Action<I, O> action, I input) {
            this.action = action;
            this.input = input;
        }

        public Trier<I, O> prepareForUnexpectedExceptionsUsing(UnexpectedExceptionHandler handler){
            return new Trier<>(this.action, this.input, handler);
        }
    }

}
