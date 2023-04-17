package br.com.stockio.use_cases.io;

import br.com.stockio.mapped_exceptions.MappedException;
import br.com.stockio.mapped_exceptions.specifics.InternalMappedException;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.io.annotations.NotBlankInputField;
import br.com.stockio.use_cases.io.annotations.NotEmptyInputField;
import br.com.stockio.use_cases.io.annotations.NotNullInputField;
import br.com.stockio.use_cases.io.annotations.ValidInnerPropertiesInputField;
import br.com.stockio.use_cases.io.exceptions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * Any use case that accepts input will require the type of it to be an
 * inheritor of this class. Once you declare your use case input as an
 * inheritor of this class, when the use case gets executed it will be able
 * to call the validateProperties() method of your use case input object
 * under the hood and check if fields are compliant to their
 * specifications, so you don't have to implement those kind of validations
 * yourself.
 */
public class UseCaseInput {

    /**
     * Every use case input instance must have its UseCaseExecutionCorrelation
     * instance within
     */
    protected final UseCaseExecutionCorrelation useCaseExecutionCorrelation;

    public UseCaseExecutionCorrelation getCorrelation(){
        return this.useCaseExecutionCorrelation;
    }

    public UseCaseInput(UseCaseExecutionCorrelation useCaseExecutionCorrelation) {
        this.useCaseExecutionCorrelation = useCaseExecutionCorrelation;
    }

    public void validateProperties(){
        try {
            if (this.useCaseExecutionCorrelation == null)
                throw new NullUseCaseExecutionCorrelationException(this.getClass().getSimpleName());
            var fields = this.getClass().getDeclaredFields();
            for (var field : fields) {
                var getterMethod = this.getGetterMethodOf(field);
                this.handleNotBlankAnnotation(field, getterMethod);
                this.handleNotEmptyAnnotation(field, getterMethod);
                this.handleValidInnerPropertiesAnnotation(field, getterMethod);
                this.handleNotNullAnnotation(field, getterMethod);
            }
        } catch (MappedException mappedException){
            throw mappedException;
        } catch (Exception e) {
            throw new InternalMappedException("Something went wrong while trying to validate properties of use case input object.",  "More details: " + e + "  | correlation ID:  " + this.useCaseExecutionCorrelation);
        }
    }

    private Method getGetterMethodOf(Field field) {
        return Arrays.stream(this.getClass().getMethods())
                .filter(method -> method.getName().equalsIgnoreCase("get".concat(field.getName())))
                .findFirst()
                .orElseThrow(() -> new GetterMethodNotFoundException(field));
    }

    private void handleNotBlankAnnotation(Field field, Method getterMethod) throws IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(NotBlankInputField.class)){
            var value = getterMethod.invoke(this);
            if (value instanceof String){
                this.checkIfNotNull(value, field);
                if (((String) value).isBlank())
                    throw new BlankFieldException(field.getName());
            } else
                throw new NotBlankAnnotationOnWrongTypeException(field.getName());
        }
    }

    private void handleNotEmptyAnnotation(Field field, Method getterMethod) throws IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(NotEmptyInputField.class)){
            var value = getterMethod.invoke(this);
            if (value instanceof String){
                this.checkIfNotNull(value, field);
                if (((String) value).isEmpty())
                    throw new EmptyFieldException(field.getName());
            } else
                throw new NotEmptyAnnotationOnWrongTypeException(field.getName());
        }
    }

    private void handleValidInnerPropertiesAnnotation(Field field, Method getterMethod) throws IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(ValidInnerPropertiesInputField.class)){
            var value = getterMethod.invoke(this);
            if (value instanceof UseCaseInput){
                this.checkIfNotNull(value, field);
                ((UseCaseInput) value).validateProperties();
            }
            else
                throw new ValidInnerPropertiesAnnotationOnWrongTypeException(field.getName());
        }
    }

    private void handleNotNullAnnotation(Field field, Method getterMethod) throws IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(NotNullInputField.class)){
            var value = getterMethod.invoke(this);
            this.checkIfNotNull(value, field);
        }
    }

    private void checkIfNotNull(Object value, Field field){
        if (Optional.ofNullable(value).isEmpty())
            throw new NullFieldException(field.getName());
    }

    public static class GetterMethodNotFoundException extends InternalMappedException {
        public GetterMethodNotFoundException(Field field) {
            super("Getter method not found for one of the fields.", "More details: the field '" + field.getName() + "' has no getter method defined for it. Please define one method for this purpose.");
        }
    }

    public static class NullUseCaseExecutionCorrelationException extends InternalMappedException {
        public NullUseCaseExecutionCorrelationException(String name) {
            super("Use case inputs must have correlation ID", "The correlation ID of " +  name + " was null");
        }
    }
}
