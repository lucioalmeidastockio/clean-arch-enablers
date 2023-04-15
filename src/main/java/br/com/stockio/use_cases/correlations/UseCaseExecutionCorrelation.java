package br.com.stockio.use_cases.correlations;


import br.com.stockio.use_cases.correlations.exceptions.CorrelationIdValueFormatException;

import java.util.UUID;

public class UseCaseExecutionCorrelation {

    private final UUID id;

    public UseCaseExecutionCorrelation(UUID id) {
        this.id = id;
    }

    public static UseCaseExecutionCorrelation of(String stringValue){
        try {
            var uuid = UUID.fromString(stringValue);
            return new UseCaseExecutionCorrelation(uuid);
        } catch (Exception exception){
            throw new CorrelationIdValueFormatException(stringValue);
        }
    }

    public UUID getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return this.id.toString();
    }

}
