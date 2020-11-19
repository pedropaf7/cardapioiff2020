package br.edu.iff.projetoCardapio.exception;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ValidationError extends Error{
    
    private List<PropertyError> errors = new ArrayList<>();
    

    public ValidationError(Calendar timestap, Integer status, String error, String message, String path) {
        super(timestap, status, error, message, path);
    }

    public List<PropertyError> getErrors() {
        return errors;
    }

    public void setErrors(List<PropertyError> errors) {
        this.errors = errors;
    }
    
    
}
