package br.edu.iff.projetoCardapio.exception;

import java.io.Serializable;
import java.util.Calendar;

public class Error implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Calendar timestap;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public Error(Calendar timestap, Integer status, String error, String message, String path) {
        this.timestap = timestap;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Calendar getTimestap() {
        return timestap;
    }

    public void setTimestap(Calendar timestap) {
        this.timestap = timestap;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}
