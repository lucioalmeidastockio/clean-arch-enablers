package br.com.stockio.exceptions;

public class ProcessException extends MappedException{

    public ProcessException(String publicMessage, String details){
        super(publicMessage, details);
    }

    public ProcessException(String publicMessage){
        super(publicMessage);
    }

}
