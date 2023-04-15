package br.com.stockio.use_cases.specifics.functions;

import br.com.stockio.loggers.Logger;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.UseCase;
import br.com.stockio.use_cases.UseCaseProcessorFactory;
import br.com.stockio.use_cases.exceptions.UseCaseExecutionException;
import br.com.stockio.use_cases.io.UseCaseInput;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;

public abstract class FunctionUseCase <I extends UseCaseInput, O> extends UseCase {

    protected FunctionUseCase(UseCaseMetadata useCaseMetadata, Logger logger) {
        super(useCaseMetadata, logger);
    }

    public O execute(I input){
        input.validateProperties();
        return Trier.of(() ->  UseCaseProcessorFactory.of(this, input.getCorrelation(), this.logger).processUseCaseUsing(input))
                    .prepareForUnexpectedExceptionsUsing(unexpectedException -> new UseCaseExecutionException(this, unexpectedException))
                .andExecuteTheAction();
    }

    protected abstract O applyInternalLogic(I input);

}
