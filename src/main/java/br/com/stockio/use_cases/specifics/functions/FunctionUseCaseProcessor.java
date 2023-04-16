package br.com.stockio.use_cases.specifics.functions;

import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.UseCaseProcessor;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;

public class FunctionUseCaseProcessor<I extends UseCaseInput, O> extends UseCaseProcessor<FunctionUseCase<I, O>> {

    public FunctionUseCaseProcessor(FunctionUseCase<I, O> useCase, UseCaseExecutionCorrelation useCaseExecutionCorrelation, Logger logger) {
        super(useCase, useCaseExecutionCorrelation, logger);
    }

    public O processUseCaseUsing(I input){
        try {
            this.logExecutionStart();
            var output = this.useCase.applyInternalLogic(input);
            this.logExecutionEnd();
            return output;
        } catch (Exception anyException){
            this.handle(anyException);
            throw anyException;
        }
    }

}
