
package br.edu.iff.projetoCardapio.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DataValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataValidation {
    String message() default "Data inválida. Coloque uma data atual ou no futuro!";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
    
}
