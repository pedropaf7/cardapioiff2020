package br.edu.iff.projetoCardapio.model;
import java.io.Serializable;
import java.util.Objects;

public class Refeicao implements Serializable{
    private static final long serialVersionUID = 1L;
     
    private Long id;
    private String nome;
    private double kcal;
    private TipoRefeicaoEnum tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public TipoRefeicaoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoRefeicaoEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Refeicao other = (Refeicao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
      
    
}
