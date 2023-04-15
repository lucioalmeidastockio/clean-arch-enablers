package br.com.stockio.trier;

import java.util.function.Supplier;

public class SupplierAction<O> implements Action<Void, O>{

    private final Supplier<O> supplier;

    SupplierAction(Supplier<O> supplier) {
        this.supplier = supplier;
    }

    @Override
    public O execute(Void input) {
        return this.supplier.get();
    }
}
