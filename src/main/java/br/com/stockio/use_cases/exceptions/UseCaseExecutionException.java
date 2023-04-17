package br.com.stockio.use_cases.exceptions;

import br.com.stockio.mapped_exceptions.specifics.InternalMappedException;
import br.com.stockio.use_cases.UseCase;

/**
 * If anything gets out of hand during a use case execution, this exception
 * is expected to be thrown.
 */
public class UseCaseExecutionException extends InternalMappedException {
    public UseCaseExecutionException(UseCase useCase, Exception unexpectedException) {
        super(
                "Something went unexpectedly wrong while executing use case of '" + useCase.getUseCaseMetadata().getName() + "'",
                "More details: " + unexpectedException
        );
    }
}
