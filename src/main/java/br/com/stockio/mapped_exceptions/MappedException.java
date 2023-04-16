package br.com.stockio.mapped_exceptions;

import java.util.Optional;

public class MappedException extends RuntimeException{

    protected final String briefPublicMessage;
    protected final String details;

    public MappedException(String briefPublicMessage, String details){
        super(briefPublicMessage + " | " + details);
        this.briefPublicMessage = briefPublicMessage;
        this.details = details;
    }

    public MappedException(String briefPublicMessage){
        super(briefPublicMessage);
        this.briefPublicMessage = briefPublicMessage;
        this.details = null;
    }

    public String getBriefPublicMessage(){
        return this.briefPublicMessage;
    }

    public Optional<String> getDetails() {
        return Optional.ofNullable(details);
    }
}
