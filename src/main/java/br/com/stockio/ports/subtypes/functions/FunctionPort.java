package br.com.stockio.ports.subtypes.functions;

import br.com.stockio.ports.Port;
import br.com.stockio.ports.exceptions.PortExecutionException;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;

public abstract class FunctionPort <I, O> extends Port {

    public O executePortOn(I input, UseCaseExecutionCorrelation correlation){
        return Trier.of(() -> this.executeLogic(input, correlation))
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }

    @SuppressWarnings("unchecked")
    public O executePortOn(UseCaseInput input){
        return Trier.of(() -> this.executeLogic((I) input, input.getCorrelation()))
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }

    protected abstract O executeLogic(I input, UseCaseExecutionCorrelation correlation);

}
