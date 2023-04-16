package br.com.stockio.use_cases.specifics.functions;

import br.com.stockio.mapped_exceptions.MappedException;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.exceptions.UseCaseExecutionException;
import br.com.stockio.use_cases.io.UseCaseInput;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.LoggerForTesting;

@ExtendWith(MockitoExtension.class)
class FunctionUseCaseTest {

    private final UseCaseExecutionCorrelation correlation = UseCaseExecutionCorrelation.ofNew();

    @Test
    void shouldCallTheValidatePropertiesMethodFromInput(){
        var input = Mockito.mock(TheFunctionUseCaseImplementationInput.class);
        Mockito.when(input.getCorrelation()).thenReturn(this.correlation);
        Mockito.doNothing().when(input).validateProperties();
        var useCase = new SomeNormalFunctionUseCaseImplementation();
        var useCaseResult = useCase.execute(input);
        Assertions.assertNotNull(useCaseResult);
        Assertions.assertEquals("Just executed my internal logic", useCaseResult);
        Mockito.verify(input, Mockito.times(1)).validateProperties();
    }

    @Test
    void shouldRunWithoutProblemsTheUseCase(){
        var input = Mockito.mock(TheFunctionUseCaseImplementationInput.class);
        Mockito.when(input.getCorrelation()).thenReturn(this.correlation);
        Mockito.doNothing().when(input).validateProperties();
        var useCase = new SomeNormalFunctionUseCaseImplementation();
        Assertions.assertDoesNotThrow(() -> useCase.execute(input));
    }

    @Test
    void shouldHandleNotExpectedExceptionDuringUseCaseExecution(){
        var input = Mockito.mock(TheFunctionUseCaseImplementationInput.class);
        Mockito.when(input.getCorrelation()).thenReturn(this.correlation);
        Mockito.doNothing().when(input).validateProperties();
        var useCase = new SomeUnexpectedProblematicFunctionUseCaseImplementation();
        Assertions.assertThrows(UseCaseExecutionException.class, () -> useCase.execute(input));
    }

    @Test
    void shouldHandleExpectedExceptionDuringUseCaseExecution(){
        var input = Mockito.mock(TheFunctionUseCaseImplementationInput.class);
        Mockito.when(input.getCorrelation()).thenReturn(this.correlation);
        Mockito.doNothing().when(input).validateProperties();
        var useCase = new SomeExpectedProblematicFunctionUseCaseImplementation();
        Assertions.assertThrows(SomeExpectedShitThatMightHappen.class, () -> useCase.execute(input));
    }

    private static class SomeNormalFunctionUseCaseImplementation extends FunctionUseCase<TheFunctionUseCaseImplementationInput, String> {
        protected SomeNormalFunctionUseCaseImplementation() {
            super(UseCaseMetadata.ofOpenAccessUseCase(SomeNormalFunctionUseCaseImplementation.class, "Just for testing"), LoggerForTesting.getSingletonInstance());
        }
        @Override
        protected String applyInternalLogic(TheFunctionUseCaseImplementationInput input) {
            return ("Just executed my internal logic");
        }
    }

    private static class SomeUnexpectedProblematicFunctionUseCaseImplementation extends FunctionUseCase<TheFunctionUseCaseImplementationInput, String>{
        protected SomeUnexpectedProblematicFunctionUseCaseImplementation() {
            super(UseCaseMetadata.ofOpenAccessUseCase(SomeUnexpectedProblematicFunctionUseCaseImplementation.class, "Just for testing"), LoggerForTesting.getSingletonInstance());
        }
        @Override
        protected String applyInternalLogic(TheFunctionUseCaseImplementationInput input) {
            throw new RuntimeException("some unexpected internal error");
        }
    }

    private static class SomeExpectedProblematicFunctionUseCaseImplementation extends FunctionUseCase<TheFunctionUseCaseImplementationInput, String>{
        protected SomeExpectedProblematicFunctionUseCaseImplementation() {
            super(UseCaseMetadata.ofOpenAccessUseCase(SomeExpectedProblematicFunctionUseCaseImplementation.class, "Just for testing"), LoggerForTesting.getSingletonInstance());
        }
        @Override
        protected String applyInternalLogic(TheFunctionUseCaseImplementationInput input) {
            throw new SomeExpectedShitThatMightHappen();
        }
    }

    private static class TheFunctionUseCaseImplementationInput extends UseCaseInput {
        public TheFunctionUseCaseImplementationInput(UseCaseExecutionCorrelation useCaseExecutionCorrelation) {
            super(useCaseExecutionCorrelation);
        }
    }

    public static class SomeExpectedShitThatMightHappen extends MappedException {

        public SomeExpectedShitThatMightHappen() {
            super("some expected internal error that could happen");
        }
    }


}
