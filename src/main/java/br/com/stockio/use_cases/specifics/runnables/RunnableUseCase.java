package br.com.stockio.use_cases.specifics.runnables;

import br.com.stockio.loggers.Logger;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.UseCase;
import br.com.stockio.use_cases.UseCaseProcessorFactory;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.exceptions.UseCaseExecutionException;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;

public abstract class RunnableUseCase extends UseCase {
    protected RunnableUseCase(UseCaseMetadata useCaseMetadata, Logger logger) {
        super(useCaseMetadata, logger);
    }
    public void execute(UseCaseExecutionCorrelation correlation){
        Trier.of(() -> UseCaseProcessorFactory.of(this, correlation, this.logger).processUseCase())
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new UseCaseExecutionException(this, unexpectedException))
                .andExecuteTheAction();
    }
    protected abstract void applyInternalLogic(UseCaseExecutionCorrelation useCaseExecutionCorrelation);;

}
