package br.com.stockio.use_cases;

import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.metadata.UseCaseMetadata;

/**
 * Abstract type of UseCase. A UseCase will have an instance of UseCaseMetadata
 * and Logger. So, any UseCase instance will be able to internally access its
 * metadata as well as interact with the logger implementation injected.
 */
public abstract class UseCase {

    /**
     * Metadata such as UseCase name, description and protection status
     */
    protected final UseCaseMetadata useCaseMetadata;

    /**
     * Logger implementation for free use.
     */
    protected final Logger logger;

    protected UseCase(UseCaseMetadata useCaseMetadata, Logger logger) {
        this.useCaseMetadata = useCaseMetadata;
        this.logger = logger;
    }
    public UseCaseMetadata getUseCaseMetadata(){
        return this.useCaseMetadata;
    }

}
