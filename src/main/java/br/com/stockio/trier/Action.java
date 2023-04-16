package br.com.stockio.trier;

public interface Action <I, O>{
    O execute(I input);

}
