package br.com.stockio.use_cases.exceptions;

import br.com.stockio.exceptions.ProcessException;

public class NotEmptyAnnotationOnWrongTypeException extends ProcessException {
    public NotEmptyAnnotationOnWrongTypeException(String name) {
        super("The not-empty annotation should only be applied upon String fields. There is an issue about it at field '" + name + "'");
    }
}
