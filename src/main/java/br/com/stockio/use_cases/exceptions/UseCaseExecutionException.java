package br.com.stockio.use_cases.exceptions;

import br.com.stockio.mapped_exceptions.specifics.InternalMappedException;
import br.com.stockio.use_cases.UseCase;

public class UseCaseExecutionException extends InternalMappedException {
    public UseCaseExecutionException(UseCase useCase, Exception unexpectedException) {
        super(
                "Something went unexpectedly wrong while executing use case of '" + useCase.getUseCaseMetadata().getName() + "'. More details: " + unexpectedException.toString(),
                "Something went unexpectedly wrong while executing use case of '" + useCase.getUseCaseMetadata().getName() + "'."
        );
    }
}
