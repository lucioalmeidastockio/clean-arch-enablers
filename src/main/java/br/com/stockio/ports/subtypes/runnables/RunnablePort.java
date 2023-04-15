package br.com.stockio.ports.subtypes.runnables;

import br.com.stockio.ports.Port;
import br.com.stockio.ports.exceptions.PortExecutionException;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;

public abstract class RunnablePort extends Port {

    public void executePort(UseCaseExecutionCorrelation correlation){
        Trier.of(this::executeLogic, correlation)
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }
    protected abstract void executeLogic(UseCaseExecutionCorrelation correlation);

}
