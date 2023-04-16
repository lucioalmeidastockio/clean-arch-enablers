package br.com.stockio.use_cases.providers;

import br.com.stockio.use_cases.UseCase;

public interface UseCaseProvider<U extends UseCase>{

    U provideInstance();

}
