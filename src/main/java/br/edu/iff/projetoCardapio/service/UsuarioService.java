package br.edu.iff.projetoCardapio.service;

import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;
    
    
    public List<Usuario> findAll(){
        return repo.findAll();     
    }
    
    public Usuario findById(Long id){
        Optional<Usuario> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException ("Usuário não encontrado.");
        }
        return result.get();
    }
    
    public Usuario save(Usuario user){
       try{
           return repo.save(user);
       }catch(Exception e){
           throw new RuntimeException("Falha ao salvar o usuário.");
       }  
        
    }
    
    public Usuario update(Usuario user){
        Usuario obj = findById(user.getId());
        return repo.save(user);
     
    }
    
    public void delete(Long id){
        Usuario obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar o usuário.");
        }
        
    }
    
}