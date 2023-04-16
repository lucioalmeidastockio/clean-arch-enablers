package br.com.stockio.trier;

public class RunnableAction implements Action<Void, Void> {

    private final Runnable runnable;

    public RunnableAction(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public Void execute(Void input) {
        this.runnable.run();
        return null;
    }
}
