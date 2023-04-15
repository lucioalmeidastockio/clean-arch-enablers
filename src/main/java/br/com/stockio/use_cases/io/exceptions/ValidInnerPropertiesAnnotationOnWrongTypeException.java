package br.com.stockio.use_cases.io.exceptions;


import br.com.stockio.mapped_exceptions.specifics.InternalMappedException;

public class ValidInnerPropertiesAnnotationOnWrongTypeException extends InternalMappedException {
    public ValidInnerPropertiesAnnotationOnWrongTypeException(String name) {
        super("The valid-inner-properties annotation should only be applied upon T (T -> T extends UseCaseInput) fields. There is an issue about it" +
                " at field '" + name + "'");
    }
}
