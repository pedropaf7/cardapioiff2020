
package br.edu.iff.projetoCardapio.annotation;

import java.util.Calendar;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataValidator implements ConstraintValidator<DataValidation, Calendar>{

    @Override
    public boolean isValid(Calendar myDate, ConstraintValidatorContext arg1) {
        myDate.set(Calendar.HOUR_OF_DAY, 0);
        myDate.set(Calendar.MINUTE, 0);
        myDate.set(Calendar.SECOND, 0);
        myDate.set(Calendar.MILLISECOND, 0);
        
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.set(Calendar.HOUR_OF_DAY, 0);
        dataAtual.set(Calendar.MINUTE, 0);
        dataAtual.set(Calendar.SECOND, 0);
        dataAtual.set(Calendar.MILLISECOND, 0);
        
        return myDate.equals(dataAtual) || myDate.after(dataAtual);
    }
    
}
