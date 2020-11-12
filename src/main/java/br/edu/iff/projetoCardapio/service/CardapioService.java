package br.edu.iff.projetoCardapio.service;

import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.model.TipoCardapioEnum;
import br.edu.iff.projetoCardapio.repository.CardapioRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class CardapioService {
    @Autowired
    private CardapioRepository repo;
    
    
    public List<Cardapio> findAll(){
        return repo.findAll();
    }
    
    public List<Cardapio> findAll(Calendar d){
        d.set(Calendar.HOUR_OF_DAY, 0);
        d.set(Calendar.MINUTE, 0);
        d.set(Calendar.SECOND, 0);
        d.set(Calendar.MILLISECOND, 0);
        
        return repo.findByData(d);
    }
    
    public Cardapio findById(Long id){
        Optional <Cardapio> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException ("Cardápio não encontrado.");
        }
        return result.get();
    }
    
    
    public Cardapio save(Cardapio c){
       Calendar d;
       //verifica se o cardapio sendo adicionado não existe no banco
       d = verificaCardapioCadastrado(c.getTipo(), c.getData());
       //adiciona somente a data sem a hora, minutos e segundos no banco
       c.setData(d);
       
       try{
           return repo.save(c);
       }catch(Exception e){
           throw new RuntimeException("Falha ao salvar o cardápio.");
       }         
        
}
    //só pode adicionar um novo cardapio, se ele já não existir. Um cardápio igual seria ter a mesma data e ser do mesmo tipo.
    private Calendar verificaCardapioCadastrado(TipoCardapioEnum t, Calendar d){
        //ja quero que salve no banco sem as horas, minutos, segundo e milisegundos, será que vai dar algum problema no futuro?
        d.set(Calendar.HOUR_OF_DAY, 0);
        d.set(Calendar.MINUTE, 0);
        d.set(Calendar.SECOND, 0);
        d.set(Calendar.MILLISECOND, 0);
        
        List<Cardapio> result = repo.findByTipoData(t,d);

         if(!result.isEmpty()){
            throw new RuntimeException("Cardapio já cadastrado");
        }
        return d; 
    }
    
    
    public Cardapio update(Cardapio c){
        Cardapio obj = findById(c.getId());
        Calendar d;
        d = verificaCardapioCadastrado(c.getTipo(), c.getData());              
              
        try{
            c.setData(d);
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar cardapio.");   
        }
    }
      
    public void delete(Long id){
        Cardapio obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao excluir o cardapio.");
    }

}

}

    
