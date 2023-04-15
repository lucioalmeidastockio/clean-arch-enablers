package br.com.stockio.use_cases.exceptions;

import br.com.stockio.exceptions.InputException;

public class EmptyFieldException extends InputException {
    public EmptyFieldException(String name) {
        super("Field '" + name + "' can't be null");
    }
}
