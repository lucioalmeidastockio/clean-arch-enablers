package br.com.stockio.ports.specifics.suppliers;


import br.com.stockio.ports.Port;
import br.com.stockio.ports.exceptions.PortExecutionException;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;

/**
 * Specific type of port: supplier ports are ports that don't have input
 * but supply something as return of their execution.
 * @param <O> the output type
 */
public abstract class SupplierPort <O> extends Port {

    /**
     * Method accessible for triggering the port execution.
     * @param correlation the correlation of the use case execution
     * @return the output expected for the port
     */
    public O executePort(UseCaseExecutionCorrelation correlation){
        return Trier.of(this::executeLogic, correlation)
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new PortExecutionException(unexpectedException, this.getName()))
                .andExecuteTheAction();
    }

    /**
     * This method is supposed to be implemented within the concrete
     * classes that will be supplier ports. It is in this method that the
     * port logic is supposed to be contained.
     * @param correlation the correlation ID being passed by one of
     *                    the public methods
     * @return the output expected for the port execution
     */
    protected abstract O executeLogic(UseCaseExecutionCorrelation correlation);

}
