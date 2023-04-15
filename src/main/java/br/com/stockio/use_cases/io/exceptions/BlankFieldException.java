package br.com.stockio.use_cases.io.exceptions;

import br.com.stockio.mapped_exceptions.specifics.InputMappedException;

public class BlankFieldException extends InputMappedException {
    public BlankFieldException(String name) {
        super("Field '" + name + "' can't be blank.");
    }
}
