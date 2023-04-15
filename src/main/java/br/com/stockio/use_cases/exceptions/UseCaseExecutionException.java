package br.com.stockio.use_cases.exceptions;

import br.com.stockio.exceptions.ProcessException;
import br.com.stockio.use_cases.UseCase;

public class UseCaseExecutionException extends ProcessException {
    public UseCaseExecutionException(UseCase useCase, Exception unexpectedException) {
        super(
                "Something went unexpectedly wrong while executing use case of '" + useCase.getUseCaseId() + "'. More details: " + unexpectedException.toString(),
                "Something went unexpectedly wrong while executing use case of '" + useCase.getUseCaseId() + "'."
        );
    }
}
