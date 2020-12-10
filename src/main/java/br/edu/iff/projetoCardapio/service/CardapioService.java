package br.edu.iff.projetoCardapio.service;

import br.edu.iff.projetoCardapio.exception.NotFoundException;
import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.repository.CardapioRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
            throw new NotFoundException ("Cardápio não encontrado.");
        }
        return result.get();
    }
    
    
    public Cardapio save(Cardapio c){
        c = verificaCardapioDataTipo(c);
       
       try{
           return repo.save(c);
       }catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
           throw new RuntimeException("Falha ao salvar o cardápio.");
       }          
    }
    
    private Cardapio verificaCardapioDataTipo(Cardapio c){
        Calendar obj = c.getData_();
        
        //já cadastra no banco somente a data, sem o tempo
        obj.set(Calendar.HOUR_OF_DAY, 0);
        obj.set(Calendar.MINUTE, 0);
        obj.set(Calendar.SECOND, 0);
        obj.set(Calendar.MILLISECOND, 0);

        Calendar dataAtual = Calendar.getInstance();
        dataAtual.set(Calendar.HOUR_OF_DAY, 0);
        dataAtual.set(Calendar.MINUTE, 0);
        dataAtual.set(Calendar.SECOND, 0);
        dataAtual.set(Calendar.MILLISECOND, 0);
        
        //valida esse cardapio para ver se a data é atual ou futura
        if(!(obj.equals(dataAtual) || obj.after(dataAtual))){
            throw new RuntimeException("Data do cardápio inválida. Coloque a data de hoje ou no futuro!");  
        }
        //verifica se já existe esse cardapio - mesma data e mesmo tipo
        List<Cardapio> result = repo.findByTipoData(c.getTipo(),obj);
        if (!result.isEmpty()) {
            throw new RuntimeException("Cardápio já cadastrado");
        }
        
        c.setData_(obj);
        
        return c; 
    }
    
    
    public Cardapio update(Cardapio c){
        Cardapio obj = findById(c.getId());          
              
        try{
            c.setData_(obj.getData_());
            List<Cardapio> result = repo.findByTipoData(c.getTipo(), c.getData_());
            if (result.size() > 1) {
                throw new RuntimeException("Cardápio já cadastrado");
            }
            return repo.save(c);
        }catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar cardápio.");   
        }
    }
      
    public void delete(Long id){
        Cardapio obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Essa ação somente é possível a partir do usuário.");
        }
    }

}

    
