package br.com.stockio.exceptions;

public class NotFoundException extends MappedException {

    public NotFoundException(String publicMessage, String details){
        super(publicMessage, details);
    }

    public NotFoundException(String message){
        super(message);
    }

}
