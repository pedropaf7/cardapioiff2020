package br.edu.iff.projetoCardapio.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

@Entity
public class Refeicao implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, updatable = true)
    @NotBlank(message = "Nome obrigatória!")
    @Length(max = 50, message = "Nome deve ter no máximo 20 caracteres!")
    private String nome;
    @Column(nullable = true, updatable = true)
    @PositiveOrZero (message = "Calorias deve ser um valor positivo ou zero!")
    private double kcal;
    @Column(nullable = false, length = 50, updatable = true)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de refeição é obrigatório!")
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
