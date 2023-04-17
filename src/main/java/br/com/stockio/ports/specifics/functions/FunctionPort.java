package br.com.stockio.ports.specifics.functions;

import br.com.stockio.ports.Port;
import br.com.stockio.ports.exceptions.PortExecutionException;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;

/**
 * Specific type of port: function ports are ports that both receive
 * inputs and return outputs.
 * @param <I> the input type
 * @param <O> the output type
 */
public abstract class FunctionPort <I, O> extends Port {

    /**
     * Method accessible for triggering the port execution.
     * @param input its input
     * @param correlation the correlation of the use case execution
     * @return the expected output of the port
     */
    public O executePortOn(I input, UseCaseExecutionCorrelation correlation){
        return this.handle(Trier.of(() -> this.executeLogic(input, correlation)));
    }

    /**
     * Method accessible for triggering the port execution when the
     * input type is an inheritor of UseCaseInput. In this case, it ain't
     * necessary to pass the correlation as second parameter as it is
     * already within the input itself, since it is an instance of UseCaseInput.
     * @param input  its input object when it is same as a UseCaseInput
     * @return the expected output of the port
     */
    @SuppressWarnings("unchecked")
    public O executePortOn(UseCaseInput input){
        return this.handle(Trier.of(() -> this.executeLogic((I) input, input.getCorrelation())));
    }

    private O handle(Trier.TrierBuilder<Void, O> trierBuilder){
        return trierBuilder .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }

    /**
     * This method is supposed to be implemented within the concrete
     * classes that will be function ports. It is in this method that the
     * port logic is supposed to be contained.
     * @param input the input to process being passed by one of the
     *              public methods
     * @param correlation the correlation ID being passed by one of
     *                    the public methods
     * @return the expected output of the port
     */
    protected abstract O executeLogic(I input, UseCaseExecutionCorrelation correlation);

}
