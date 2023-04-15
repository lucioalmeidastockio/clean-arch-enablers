package br.com.stockio.ports.exceptions;


import br.com.stockio.mapped_exceptions.specifics.InternalMappedException;

public class PortExecutionException extends InternalMappedException {
    public PortExecutionException(Exception unexpectedException, String name) {
        super("Something went unexpectedly wrong while trying to execute port '" + name + "'", "More details: " + unexpectedException);
    }
}
