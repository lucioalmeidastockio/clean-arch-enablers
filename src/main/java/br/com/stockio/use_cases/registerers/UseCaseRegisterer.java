package br.com.stockio.use_cases.registerers;


import br.com.stockio.use_cases.metadata.UseCaseMetadata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UseCaseRegisterer {
    private final BufferedWriter fileWriter;
    public static void runOn(List<UseCaseMetadata> useCases) {
        var registerer = UseCaseRegisterer.defaultInstance();
        useCases.forEach(registerer::externalizeUseCase);
        registerer.endRegistering();
    }
    private static UseCaseRegisterer defaultInstance() {
        return new UseCaseRegisterer();
    }
    private UseCaseRegisterer(){
        try {
            this.fileWriter = new BufferedWriter(new FileWriter("target/use_cases.txt"));
        } catch (IOException e) {
            throw new ExternalizeUseCaseException(e);
        }
    }
    private void externalizeUseCase(UseCaseMetadata useCaseMetadata) {
        try {
            this.fileWriter.write("| Name: " + useCaseMetadata.getName() + " | Description: " + useCaseMetadata.getDescription() + " | Protected: " + useCaseMetadata.isProtected());
            this.fileWriter.newLine();
        } catch (Exception e) {
            throw new ExternalizeUseCaseException(e);
        }
    }
    private void endRegistering() {
        try {
            this.fileWriter.close();
        } catch (IOException e) {
            throw new ExternalizeUseCaseException(e);
        }
    }
    private static class ExternalizeUseCaseException extends RuntimeException {
        public ExternalizeUseCaseException(Exception e) {
            super("Something went wrong while trying to externalize use cases info. More details: " + e.toString());
        }
    }

}
