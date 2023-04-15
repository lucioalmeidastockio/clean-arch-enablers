package br.com.stockio.mappers;

public interface Mapper<I, O>{

    O map(I input);

}
