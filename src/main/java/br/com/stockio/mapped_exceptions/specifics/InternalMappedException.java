package br.com.stockio.mapped_exceptions.specifics;

import br.com.stockio.mapped_exceptions.MappedException;

public class InternalMappedException extends MappedException {

    public InternalMappedException(String briefPublicMessage, String details){
        super(briefPublicMessage, details);
    }

}
