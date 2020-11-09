package br.edu.iff.projetoCardapio.model;

import br.edu.iff.projetoCardapio.annotation.EmailValidation;
import br.edu.iff.projetoCardapio.annotation.FuncaoValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, updatable = true)
    @NotBlank(message = "Nome obrigatório!")
    @Length(max = 50, message = "Nome deve ter no máximo 50 caracteres!")
    private String nome;
    @Column(nullable = false, length = 30, updatable = true)
    @NotBlank(message = "Função obrigatória!")
    @Length(max = 50, message = "Função deve ter no máximo 50 caracteres!")
    @FuncaoValidation
    private String funcao;
    @Column(nullable = false, length = 50, unique = true, updatable = false)
    @NotBlank(message = "E-mail obrigatório!")
    @EmailValidation
    private String email;
    @Column(nullable = false, length = 255, updatable = true)
    @NotBlank(message = "Senha obrigatória!")
    @Length(min = 8, message = "Senha deve ter no mínimo 8 caracteres!")
    private String senha;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Valid
    private List<Cardapio> cardapios = new ArrayList<>();

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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Cardapio> getCardapios() {
        return cardapios;
    }

    public void setCardapios(List<Cardapio> cardapios) {
        this.cardapios = cardapios;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
     
}
