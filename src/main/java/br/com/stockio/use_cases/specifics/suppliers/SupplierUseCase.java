package br.com.stockio.use_cases.specifics.suppliers;

import br.com.stockio.loggers.Logger;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.UseCase;
import br.com.stockio.use_cases.UseCaseProcessorFactory;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.exceptions.UseCaseExecutionException;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;

/**
 * Specific type of UseCase which has no input but provides output
 * @param <O> type of output
 */
public abstract class SupplierUseCase <O> extends UseCase {

    protected SupplierUseCase(UseCaseMetadata useCaseMetadata, Logger logger) {
        super(useCaseMetadata, logger);
    }

    /**
     * Public method which triggers the execution of the SupplierUseCase.
     * It will internally call the method which keeps the core logic of the
     * use case. If anything goes unexpectedly wrong during its execution,
     * it will throw a UseCaseExecutionException instance with the description
     * of what have gone wrong. If your use case implementation throws
     * a MappedException instance, it will not intercede as it will consider
     * the MappedException as part of the planned flow.
     * Executing your use case with this method assures there will be
     * automated log tracking control: the beginning and the ending of the
     * use case execution will be logged, weather it ends successfully or not.
     * However, you are still free to use your logger instance as you wish
     * inside your use case implementations.
     * @param correlation unique identifier of the use case execution
     * @return the use case execution output
     */
    public O execute(UseCaseExecutionCorrelation correlation){
        return Trier.of(() -> UseCaseProcessorFactory.of(this, correlation, this.logger).processUseCase())
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new UseCaseExecutionException(this, unexpectedException))
                .andExecuteTheAction();
    }

    /**
     * Internal method supposed to execute the core logic of the use case
     * @param useCaseExecutionCorrelation unique identifier of the use case execution
     * @return the use case execution output
     */
    protected abstract O applyInternalLogic(UseCaseExecutionCorrelation useCaseExecutionCorrelation);

}
