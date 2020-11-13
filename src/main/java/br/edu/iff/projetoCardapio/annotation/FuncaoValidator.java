 
package br.edu.iff.projetoCardapio.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FuncaoValidator implements ConstraintValidator<FuncaoValidation, String>{
    @Override
    public boolean isValid(String myFuncao, ConstraintValidatorContext arg1) {
        List<String> funcoes;
        funcoes = new ArrayList<>(Arrays.asList(
                "Nutricionista",
                "Cozinheira",
                "Estagiária",
                "Bolsista", 
                "Funcionária"));
        
        if(myFuncao == null) return false;
        if(myFuncao.contains(" ")) return false;
        return funcoes.contains(myFuncao);
    }  
}
