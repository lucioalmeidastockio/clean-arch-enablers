package br.com.stockio.exceptions;

public class AuthenticationException extends MappedException{

    public AuthenticationException(String details){
        super(
                "Not authenticated.",
                "More details: " + details);
    }

}
