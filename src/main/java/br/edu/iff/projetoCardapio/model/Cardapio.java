package br.edu.iff.projetoCardapio.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Cardapio implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Calendar data;
    private String abertura;
    private String encerramento;
    private TipoCardapioEnum tipo;
    
    private List<Refeicao> refeicoes;   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(String encerramento) {
        this.encerramento = encerramento;
    }

    public TipoCardapioEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoCardapioEnum tipo) {
        this.tipo = tipo;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cardapio other = (Cardapio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
