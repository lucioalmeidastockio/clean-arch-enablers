package br.com.stockio.mapped_exceptions.specifics;

import br.com.stockio.mapped_exceptions.MappedException;

public class InputMappedException extends MappedException {

    public InputMappedException(String briefPublicMessage, String details){
        super(briefPublicMessage, details);
    }

    public InputMappedException(String briefPublicMessage){
        super(briefPublicMessage);
    }


}
