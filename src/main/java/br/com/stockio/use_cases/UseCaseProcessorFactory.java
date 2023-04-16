package br.com.stockio.use_cases;


import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;
import br.com.stockio.use_cases.specifics.consumers.ConsumerUseCase;
import br.com.stockio.use_cases.specifics.consumers.ConsumerUseCaseProcessor;
import br.com.stockio.use_cases.specifics.functions.FunctionUseCase;
import br.com.stockio.use_cases.specifics.functions.FunctionUseCaseProcessor;
import br.com.stockio.use_cases.specifics.runnables.RunnableUseCase;
import br.com.stockio.use_cases.specifics.runnables.RunnableUseCaseProcessor;
import br.com.stockio.use_cases.specifics.suppliers.SupplierUseCase;
import br.com.stockio.use_cases.specifics.suppliers.SupplierUseCaseProcessor;

public class UseCaseProcessorFactory {

    private UseCaseProcessorFactory(){}

    public static <I extends UseCaseInput, O> FunctionUseCaseProcessor<I, O> of(
            FunctionUseCase<I, O> useCase,
            UseCaseExecutionCorrelation useCaseExecutionCorrelation,
            Logger logger){
        return new FunctionUseCaseProcessor<>(useCase, useCaseExecutionCorrelation, logger);
    }

    public static <I extends UseCaseInput> ConsumerUseCaseProcessor<I> of(
            ConsumerUseCase<I> useCase,
            UseCaseExecutionCorrelation useCaseExecutionCorrelation,
            Logger logger){
        return new ConsumerUseCaseProcessor<>(useCase, useCaseExecutionCorrelation, logger);
    }

    public static <O> SupplierUseCaseProcessor<O> of(
            SupplierUseCase<O> useCase,
            UseCaseExecutionCorrelation useCaseExecutionCorrelation,
            Logger logger){
        return new SupplierUseCaseProcessor<>(useCase, useCaseExecutionCorrelation, logger);
    }

    public static RunnableUseCaseProcessor of(
            RunnableUseCase useCase,
            UseCaseExecutionCorrelation useCaseExecutionCorrelation,
            Logger logger){
        return new RunnableUseCaseProcessor(useCase, useCaseExecutionCorrelation, logger);
    }
}
