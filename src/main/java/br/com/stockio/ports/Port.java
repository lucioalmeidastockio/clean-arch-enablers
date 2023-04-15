package br.com.stockio.ports;

public abstract class Port {

    protected final String name;

    protected Port() {
        this.name = this.getClass().getSimpleName();
    }

    public String getName(){
        return this.name;
    }

}
