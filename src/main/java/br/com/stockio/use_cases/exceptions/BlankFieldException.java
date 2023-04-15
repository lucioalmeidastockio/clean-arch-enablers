package br.com.stockio.use_cases.exceptions;

import br.com.stockio.exceptions.InputException;

public class BlankFieldException extends InputException {
    public BlankFieldException(String name) {
        super("Field '" + name + "' can't be blank.");
    }
}
