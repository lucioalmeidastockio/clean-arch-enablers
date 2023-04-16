package br.com.stockio.use_cases.io.exceptions;

import br.com.stockio.mapped_exceptions.specifics.InternalMappedException;

public class NotBlankAnnotationOnWrongTypeException extends InternalMappedException {
    public NotBlankAnnotationOnWrongTypeException(String name) {
        super("The not-blank annotation should only be applied upon String fields.", "There is an issue about it at field '" + name + "'");
    }
}
