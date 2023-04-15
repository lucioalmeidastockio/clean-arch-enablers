package br.com.stockio.exceptions;

public class AuthorizationException extends MappedException{

    public AuthorizationException(String details){
        super("Authorization denied.",
                "More details: '".concat(details).concat("'"));
    }

}
