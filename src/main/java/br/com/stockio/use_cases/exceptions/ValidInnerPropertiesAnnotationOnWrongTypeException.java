package br.com.stockio.use_cases.exceptions;


import br.com.stockio.exceptions.ProcessException;

public class ValidInnerPropertiesAnnotationOnWrongTypeException extends ProcessException {
    public ValidInnerPropertiesAnnotationOnWrongTypeException(String name) {
        super("The valid-inner-properties annotation should only be applied upon T (T -> T extends UseCaseInput) fields. There is an issue about it" +
                " at field '" + name + "'");
    }
}
