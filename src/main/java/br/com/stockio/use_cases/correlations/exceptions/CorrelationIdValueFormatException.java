package br.com.stockio.use_cases.correlations.exceptions;

public class CorrelationIdValueFormatException extends RuntimeException {
    public CorrelationIdValueFormatException(String stringValue) {
        super("The string value '"+stringValue+"' cant be used as a correlation ID. Correlation IDs must be unique, therefore they must be in UUID " +
                "format.");
    }
}
