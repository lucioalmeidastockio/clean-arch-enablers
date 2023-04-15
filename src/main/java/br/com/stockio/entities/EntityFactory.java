package br.com.stockio.entities;

public interface EntityFactory <E extends Entity>{

    E makeNewInstance();

}
