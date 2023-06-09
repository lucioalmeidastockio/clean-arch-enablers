package br.com.stockio.ports.specifics.consumers;


import br.com.stockio.ports.Port;
import br.com.stockio.ports.exceptions.PortExecutionException;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;

/**
 * Specific type of port: consumer ports are ports that receive inputs
 * but don't return anything.
 * @param <I> input type
 */
public abstract class ConsumerPort <I> extends Port {

    /**
     * Method accessible for triggering the port execution.
     * @param input its input object
     * @param correlation the correlation of the use case execution
     */
    public void executePortOn(I input, UseCaseExecutionCorrelation correlation){
        this.handle(Trier.of(() -> this.executeLogic(input, correlation)));
    }

    private void handle(Trier.TrierBuilder<Void, Void> trierBuilder){
        trierBuilder .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }

    /**
     * This method is supposed to be implemented within the concrete
     * classes that will be consumer ports. It is in this method that the
     * port logic is supposed to be contained.
     * @param input the input to process being passed by one of the
     *              public methods
     * @param correlation the correlation ID being passed by one of
     *              the public methods
     */
    protected abstract void executeLogic(I input, UseCaseExecutionCorrelation correlation);

}
