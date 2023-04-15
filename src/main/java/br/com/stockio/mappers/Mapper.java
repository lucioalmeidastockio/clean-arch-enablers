package br.com.stockio.mappers;

public interface Mapper<I, O>{

    O mapFrom(I input);

}
