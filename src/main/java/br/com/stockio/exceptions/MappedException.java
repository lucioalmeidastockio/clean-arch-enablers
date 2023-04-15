package br.com.stockio.exceptions;

import java.util.Optional;

public class MappedException extends RuntimeException{

    protected final String publicMessage;
    protected final String details;

    public MappedException(String publicMessage, String details){
        super(publicMessage + " | " + details);
        this.publicMessage = publicMessage;
        this.details = details;
    }

    public MappedException(String publicMessage){
        super(publicMessage);
        this.publicMessage = publicMessage;
        this.details = null;
    }

    public String getPublicMessage(){
        return this.publicMessage;
    }

    public Optional<String> getDetails() {
        return Optional.ofNullable(details);
    }
}
