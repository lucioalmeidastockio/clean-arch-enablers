package br.com.stockio.ports.subtypes.suppliers;


import br.com.stockio.ports.Port;
import br.com.stockio.ports.exceptions.PortExecutionException;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;

public abstract class SupplierPort <O> extends Port {

    public O executePort(UseCaseExecutionCorrelation correlation){
        return Trier.of(this::executeLogic, correlation)
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }
    protected abstract O executeLogic(UseCaseExecutionCorrelation correlation);

}
