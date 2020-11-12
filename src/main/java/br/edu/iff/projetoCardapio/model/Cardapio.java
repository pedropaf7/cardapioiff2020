package br.edu.iff.projetoCardapio.model;

import br.edu.iff.projetoCardapio.annotation.DataValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cardapio implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = true)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data do cardápio deve ser obrigatória!")
    @DataValidation
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar data_;
    @Column(nullable = false, length = 20, updatable = true)
    @NotBlank(message = "Hora de abertura obrigatória!")
    @Length(max = 50, message = "Hora de abertura deve ter no máximo 20 caracteres!")
    private String abertura;
    @Column(nullable = false, length = 20, updatable = true)
    @NotBlank(message = "Hora de encerramento obrigatória!")
    @Length(max = 50, message = "Hora de encerramento deve ter no máximo 20 caracteres!")
    private String encerramento;
    @Column(nullable = false, length = 30, updatable = true)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de cardápio deve ser obrigatório!")
    //@Length(max = 30, message = "Tipo de cardápio deve ter no máximo 30 caracteres!")
    private TipoCardapioEnum tipo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cardapio", nullable = false)
    @Size(min = 1, message = "Cardápio deve ter pelo menos uma refeição")
    @Valid
    private List<Refeicao> refeicoes = new ArrayList<>();   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getData() {
        return data_;
    }

    public void setData(Calendar data) {
        this.data_ = data;
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
