package br.com.stockio.use_cases.specifics.consumers;

import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.UseCaseProcessor;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;

public class ConsumerUseCaseProcessor<I extends UseCaseInput> extends UseCaseProcessor<ConsumerUseCase<I>> {

    public ConsumerUseCaseProcessor(ConsumerUseCase<I> useCase, UseCaseExecutionCorrelation useCaseExecutionCorrelation, Logger logger) {
        super(useCase, useCaseExecutionCorrelation, logger);
    }

    public void processUseCaseUsing(I input){
        try {
            this.logExecutionStart();
            this.useCase.applyInternalLogic(input, this.useCaseExecutionCorrelation);
            this.logExecutionEnd();
        } catch (Exception anyException){
            this.handle(anyException);
            throw anyException;
        }
    }
}
