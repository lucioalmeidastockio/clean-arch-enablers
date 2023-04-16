package br.com.stockio.mapped_exceptions.specifics;

import br.com.stockio.mapped_exceptions.MappedException;

public class NotFoundMappedException extends MappedException {

    public NotFoundMappedException(String briefPublicMessage, String details){
        super(briefPublicMessage, details);
    }

    public NotFoundMappedException(String briefPublicMessage){
        super(briefPublicMessage);
    }

}
