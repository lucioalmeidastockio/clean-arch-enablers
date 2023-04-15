package br.com.stockio.loggers;

public interface Logger {

    void logInfo(String info);
    void logError(String error);
    void logDebug(String info);

}
