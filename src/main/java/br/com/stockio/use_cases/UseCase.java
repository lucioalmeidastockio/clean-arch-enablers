package br.com.stockio.use_cases;

import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;

public abstract class UseCase {

    protected final UseCaseMetadata useCaseMetadata;
    protected final Logger logger;

    protected UseCase(UseCaseMetadata useCaseMetadata, Logger logger) {
        this.useCaseMetadata = useCaseMetadata;
        this.logger = logger;
    }

    public String getUseCaseId(){
        return this.useCaseMetadata.getName();
    }
    public UseCaseMetadata getUseCaseMetadata(){
        return this.useCaseMetadata;
    }

}
