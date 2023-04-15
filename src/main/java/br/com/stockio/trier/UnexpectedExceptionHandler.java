package br.com.stockio.trier;


import br.com.stockio.exceptions.MappedException;

@FunctionalInterface
public interface UnexpectedExceptionHandler {

    MappedException handle(Exception unexpectedException);

}
