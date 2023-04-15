package br.com.stockio.ports.subtypes.consumers;


import br.com.stockio.ports.Port;
import br.com.stockio.ports.exceptions.PortExecutionException;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;

public abstract class ConsumerPort <I> extends Port {

    public void executePortOn(I input, UseCaseExecutionCorrelation correlation){
        Trier.of(() -> this.executeLogic(input, correlation))
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }

    @SuppressWarnings("unchecked")
    public void executePortOn(UseCaseInput input){
        Trier.of(() -> this.executeLogic((I) input, input.getCorrelation()))
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }

    protected abstract void executeLogic(I input, UseCaseExecutionCorrelation correlation);

}
