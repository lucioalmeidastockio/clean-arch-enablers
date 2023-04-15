package br.com.stockio.use_cases.exceptions;

import br.com.stockio.exceptions.ProcessException;

public class NotBlankAnnotationOnWrongTypeException extends ProcessException {
    public NotBlankAnnotationOnWrongTypeException(String name) {
        super("The not-blank annotation should only be applied upon String fields. There is an issue about it at field '" + name + "'");
    }
}
