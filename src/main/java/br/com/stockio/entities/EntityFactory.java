package br.com.stockio.entities;

public interface EntityFactory <@Entity E>{

    E makeNewInstance();

}
