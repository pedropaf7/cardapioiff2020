package br.edu.iff.projetoCardapio.repository;

import br.edu.iff.projetoCardapio.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
