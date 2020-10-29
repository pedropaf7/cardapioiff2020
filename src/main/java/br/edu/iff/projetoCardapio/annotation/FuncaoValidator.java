
package br.edu.iff.projetoCardapio.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FuncaoValidator implements ConstraintValidator<EmailValidation, String>{
    private List<String> funcoes = new ArrayList<String>(Arrays.asList(
            "Nutricionista", 
            "Cozinheira", 
            "Estagiária", 
            "Bolsista", 
            "Funcionária")); 
    
    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if(arg0 == null) return false;
        if(arg0.contains("")) return false;
        return funcoes.contains(arg0);
    }  
}
