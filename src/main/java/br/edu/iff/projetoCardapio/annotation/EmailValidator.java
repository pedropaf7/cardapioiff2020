
package br.edu.iff.projetoCardapio.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String>{

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if(arg0 == null) return false;
        if(arg0.contains(" ")) return false;
        return arg0.matches("[\\w\\s]+[@]+[\\w\\s]+[.]+[\\w\\s]+");
        
    }
    
    
}
