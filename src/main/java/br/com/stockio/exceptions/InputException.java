package br.com.stockio.exceptions;

public class InputException extends MappedException{

    public InputException(String publicMessage, String details){
        super(publicMessage, details);
    }

    public InputException(String message){
        super(message);
    }


}
