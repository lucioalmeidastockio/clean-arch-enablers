package br.com.stockio.ports.specifics.functions;

import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.UseCaseInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FunctionPortTest {

    @Mock
    private UseCaseExecutionCorrelation correlation;
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp(){
        Mockito.when(this.correlation.getId()).thenReturn(this.id);
    }

    @Test
    void shouldExecuteThePortImplementationLogicAsExpected(){
        var portImplementation = new SomeFunctionPortImplementation();
        var stringInput = "input";
        var portResult = portImplementation.executePortOn(stringInput, this.correlation);
        Assertions.assertFalse(portResult);
        Mockito.verify(this.correlation, Mockito.times(1)).getId();
    }

    @Test
    void shouldExecuteThePortImplementationLogicAsExpectedWhenInputIsSomeUseCaseInputType(){
        var portImplementation = new SomeFunctionPortImplementationWithUseCaseInputAsItsOwnInput();
        var input = new SomeUseCaseInput(this.correlation);
        var portResult = portImplementation.executePortOn(input);
        Assertions.assertFalse(portResult);
        Mockito.verify(this.correlation, Mockito.times(1)).getId();
    }

    private static class SomeFunctionPortImplementation extends FunctionPort<String, Boolean>{
        @Override
        protected Boolean executeLogic(String input, UseCaseExecutionCorrelation correlation) {
            return input.isBlank() || correlation.getId().toString().isBlank();
        }
    }

    private static class SomeFunctionPortImplementationWithUseCaseInputAsItsOwnInput extends FunctionPort<SomeUseCaseInput, Boolean>{
        @Override
        protected Boolean executeLogic(SomeUseCaseInput input, UseCaseExecutionCorrelation correlation) {
            return input.getCorrelation().getId().toString().isBlank();
        }
    }

    private static class SomeUseCaseInput extends UseCaseInput{

        public SomeUseCaseInput(UseCaseExecutionCorrelation useCaseExecutionCorrelation) {
            super(useCaseExecutionCorrelation);
        }
    }

}
