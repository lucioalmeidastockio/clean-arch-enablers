package br.com.stockio.use_cases.io.exceptions;

import br.com.stockio.mapped_exceptions.specifics.InputMappedException;

public class EmptyFieldException extends InputMappedException {
    public EmptyFieldException(String name) {
        super("Field '" + name + "' can't be null");
    }
}
