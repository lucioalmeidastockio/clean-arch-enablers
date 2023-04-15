package br.com.stockio.use_cases.exceptions;


import br.com.stockio.exceptions.InputException;

public class NullFieldException extends InputException {
    public NullFieldException(String name) {
        super("Field '" + name + "' can't be null");
    }
}
