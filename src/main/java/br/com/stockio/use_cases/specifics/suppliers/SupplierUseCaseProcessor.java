package br.com.stockio.use_cases.specifics.suppliers;

import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.UseCaseProcessor;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;

public class SupplierUseCaseProcessor<O> extends UseCaseProcessor<SupplierUseCase<O>> {

    public SupplierUseCaseProcessor(SupplierUseCase<O> useCase, UseCaseExecutionCorrelation useCaseExecutionCorrelation, Logger logger) {
        super(useCase, useCaseExecutionCorrelation, logger);
    }

    public O processUseCase(){
        try {
            this.logExecutionStart();
            var output = this.useCase.applyInternalLogic(this.useCaseExecutionCorrelation);
            this.logExecutionEnd();
            return output;
        } catch (Exception anyException){
            this.handle(anyException);
            throw anyException;
        }
    }

}
