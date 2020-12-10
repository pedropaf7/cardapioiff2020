package br.edu.iff.projetoCardapio.repository;

import br.edu.iff.projetoCardapio.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email); 
    
    @Query("SELECT user FROM Usuario user WHERE user.nome = :nome")
    public List<Usuario> findByNome(@Param("nome") String nome); 
}
