package br.com.stockio.ports.exceptions;


import br.com.stockio.exceptions.ProcessException;

public class PortExecutionException extends ProcessException {
    public PortExecutionException(Exception unexpectedException, String name) {
        super("Something went unexpectedly wrong while trying to execute port '" + name + "'");
    }
}
