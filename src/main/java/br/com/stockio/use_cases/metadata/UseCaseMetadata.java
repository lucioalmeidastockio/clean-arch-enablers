package br.com.stockio.use_cases.metadata;

import br.com.stockio.use_cases.UseCase;

public class UseCaseMetadata {

    private final String name;
    private final Boolean isProtected;
    private final String description;

    public static  <U extends UseCase> UseCaseMetadata ofProtectedUseCase(Class<U> useCaseType, String description){
        return new UseCaseMetadata(useCaseType, description, true);
    }

    public static <U extends UseCase> UseCaseMetadata ofOpenAccessUseCase(Class<U> useCaseType, String description){
        return new UseCaseMetadata(useCaseType, description, false);
    }

    private <U extends UseCase> UseCaseMetadata(Class<U> useCaseType, String description, Boolean isProtected) {
        this.name = getNameOutta(useCaseType);
        this.isProtected = isProtected;
        this.description = description;
    }

    private <U extends UseCase> String getNameOutta(Class<U> useCaseType) {
        var simpleName = useCaseType.getSimpleName();
        var snakeCaseName = new StringBuilder();
        var index = 0;
        for (var character : simpleName.toCharArray()){
            if (Character.isUpperCase(character) && index > 0)
                snakeCaseName.append("_");
            snakeCaseName.append(String.valueOf(character).toLowerCase());
            index ++;
        }
        return snakeCaseName.toString().replace("_use_case", "");
    }

    public String getName() {
        return name;
    }

    public Boolean isProtected() {
        return isProtected;
    }

    public String getDescription(){
        return this.description;
    }
}
