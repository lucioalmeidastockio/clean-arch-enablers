package br.com.stockio.use_cases;


import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;
import br.com.stockio.use_cases.subtypes.consumers.ConsumerUseCase;
import br.com.stockio.use_cases.subtypes.consumers.ConsumerUseCaseProcessor;
import br.com.stockio.use_cases.subtypes.functions.FunctionUseCase;
import br.com.stockio.use_cases.subtypes.functions.FunctionUseCaseProcessor;
import br.com.stockio.use_cases.subtypes.runnables.RunnableUseCase;
import br.com.stockio.use_cases.subtypes.runnables.RunnableUseCaseProcessor;
import br.com.stockio.use_cases.subtypes.suppliers.SupplierUseCase;
import br.com.stockio.use_cases.subtypes.suppliers.SupplierUseCaseProcessor;

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
