package br.com.stockio.use_cases.io;

import br.com.stockio.exceptions.ProcessException;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.exceptions.*;
import br.com.stockio.use_cases.io.validators.NotBlankInputField;
import br.com.stockio.use_cases.io.validators.NotEmptyInputField;
import br.com.stockio.use_cases.io.validators.NotNullInputField;
import br.com.stockio.use_cases.io.validators.ValidInnerPropertiesInputField;

import java.lang.reflect.Field;
import java.util.Optional;

public class UseCaseInput {

    protected final UseCaseExecutionCorrelation useCaseExecutionCorrelation;

    public UseCaseExecutionCorrelation getCorrelation(){
        return this.useCaseExecutionCorrelation;
    }

    public UseCaseInput(UseCaseExecutionCorrelation useCaseExecutionCorrelation) {
        this.useCaseExecutionCorrelation = useCaseExecutionCorrelation;
    }

    public void validateProperties(){
        try {
            var fields = this.getClass().getFields();
            for (var field : fields){
                field.setAccessible(true);
                this.handleNotBlankAnnotation(field);
                this.handleNotEmptyAnnotation(field);
                this.handleValidInnerPropertiesAnnotation(field);
                this.handleNotNullAnnotation(field);
            }
        } catch (Exception e) {
            throw new ProcessException("Something went wrong while trying to validate properties of use case input object. More details: " + e + "  |  " + this.useCaseExecutionCorrelation);
        }
    }

    private void handleNotBlankAnnotation(Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(NotBlankInputField.class)){
            var value = field.get(this);
            if (value instanceof String){
                this.checkIfNotNull(value, field);
                if (((String) value).isBlank())
                    throw new BlankFieldException(field.getName());
            } else
                throw new NotBlankAnnotationOnWrongTypeException(field.getName());
        }
    }

    private void handleNotEmptyAnnotation(Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(NotEmptyInputField.class)){
            var value = field.get(this);
            if (value instanceof String){
                this.checkIfNotNull(value, field);
                if (((String) value).isEmpty())
                    throw new EmptyFieldException(field.getName());
            } else
                throw new NotEmptyAnnotationOnWrongTypeException(field.getName());
        }
    }

    private void handleValidInnerPropertiesAnnotation(Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(ValidInnerPropertiesInputField.class)){
            var value = field.get(this);
            if (value instanceof UseCaseInput){
                this.checkIfNotNull(value, field);
                ((UseCaseInput) value).validateProperties();
            }
            else
                throw new ValidInnerPropertiesAnnotationOnWrongTypeException(field.getName());
        }
    }

    private void handleNotNullAnnotation(Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(NotNullInputField.class)){
            var value = field.get(this);
            this.checkIfNotNull(value, field);
        }
    }

    private void checkIfNotNull(Object value, Field field){
        if (Optional.ofNullable(value).isEmpty())
            throw new NullFieldException(field.getName());
    }

}
