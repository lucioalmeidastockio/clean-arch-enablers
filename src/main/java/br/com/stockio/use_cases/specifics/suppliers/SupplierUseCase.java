package br.com.stockio.use_cases.specifics.suppliers;

import br.com.stockio.loggers.Logger;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.UseCase;
import br.com.stockio.use_cases.UseCaseProcessorFactory;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.exceptions.UseCaseExecutionException;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;

public abstract class SupplierUseCase <O> extends UseCase {

    protected SupplierUseCase(UseCaseMetadata useCaseMetadata, Logger logger) {
        super(useCaseMetadata, logger);
    }

    public O execute(UseCaseExecutionCorrelation correlation){
        return Trier.of(() -> UseCaseProcessorFactory.of(this, correlation, this.logger).processUseCase())
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new UseCaseExecutionException(this, unexpectedException))
                .andExecuteTheAction();
    }

    protected abstract O applyInternalLogic(UseCaseExecutionCorrelation useCaseExecutionCorrelation);

}
