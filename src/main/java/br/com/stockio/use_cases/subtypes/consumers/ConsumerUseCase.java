package br.com.stockio.use_cases.subtypes.consumers;

import br.com.stockio.loggers.Logger;
import br.com.stockio.trier.Trier;
import br.com.stockio.use_cases.UseCase;
import br.com.stockio.use_cases.UseCaseProcessorFactory;
import br.com.stockio.use_cases.exceptions.UseCaseExecutionException;
import br.com.stockio.use_cases.io.UseCaseInput;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;

public abstract class ConsumerUseCase <I extends UseCaseInput> extends UseCase {

    protected ConsumerUseCase(UseCaseMetadata useCaseMetadata, Logger logger) {
        super(useCaseMetadata, logger);
    }

    public void execute(I input){
        input.validateProperties();
        Trier.of(() ->  UseCaseProcessorFactory.of(this, input.getCorrelation(), this.logger).processUseCaseUsing(input))
                .prepareForUnexpectedExceptionsUsing(unexpectedException -> new UseCaseExecutionException(this, unexpectedException))
                .andExecuteTheAction();
    }

    protected abstract void applyInternalLogic(I input);


}
