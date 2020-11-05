package br.edu.iff.projetoCardapio.repository;

import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.model.TipoCardapioEnum;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long>{
    @Query("SELECT c FROM Cardapio c WHERE c.data_ = :data_")
    public List<Cardapio> findByData(@Param("data_")Calendar data_);
    
    public List<Cardapio> findByTipo(TipoCardapioEnum tipo);
    
    @Query("SELECT c FROM Cardapio c JOIN c.refeicoes r WHERE r.nome = :nome")
    public List<Cardapio> findByRefeicao(@Param("nome")String nome);
    
    @Query("SELECT c FROM Cardapio c where c.data_ BETWEEN :inicio AND :fim")
    public List<Cardapio> findCardapioEntreDatas(Calendar inicio, Calendar fim);

}