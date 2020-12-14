package br.edu.iff.projetoCardapio.controller.apirest;

import br.edu.iff.projetoCardapio.exception.Error;
import br.edu.iff.projetoCardapio.exception.NotFoundException;
import br.edu.iff.projetoCardapio.exception.PropertyError;
import br.edu.iff.projetoCardapio.exception.ValidationError;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Calendar;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;

@RestControllerAdvice(annotations = RestController.class)
public class MyRestControllerAdvice {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity erroValidacao(ConstraintViolationException e, HttpServletRequest request){
        ValidationError erro = new ValidationError(
                Calendar.getInstance(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "Erro de validação.",
                request.getRequestURI());
       for (ConstraintViolation cv : e.getConstraintViolations()){
           PropertyError p = new PropertyError(cv.getPropertyPath().toString(), cv.getMessage());
           erro.getErrors().add(p);
       }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);  
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erroValidacao(MethodArgumentNotValidException e, HttpServletRequest request){
    ValidationError erro = new ValidationError(
                Calendar.getInstance(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "Erro de validação.",
                request.getRequestURI());
       for (FieldError fe : e.getBindingResult().getFieldErrors()){
           PropertyError p = new PropertyError(fe.getField(), fe.getDefaultMessage());
           erro.getErrors().add(p);
       }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);  
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity erroPadrao(NotFoundException e, HttpServletRequest request){
        Error erro = new Error(
                Calendar.getInstance(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                e.getMessage(),
                request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);  
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity erroPadrao(Exception e, HttpServletRequest request){
        Error erro = new Error(
                Calendar.getInstance(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage(),
                request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);  
    }
    
}
