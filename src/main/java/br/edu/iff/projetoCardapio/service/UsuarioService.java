package br.edu.iff.projetoCardapio.service;

import br.edu.iff.projetoCardapio.exception.NotFoundException;
import br.edu.iff.projetoCardapio.model.Permissao;
import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            throw new NotFoundException ("Usuário não encontrado.");
        }
        return result.get();
    }
    
    public Usuario findByEmail(String email) {
        return repo.findByEmail(email);
    }
    
    public Usuario save(Usuario user){
       if(findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Email já cadastrado");
        }
       //verifica permissões nulas
        removePermissoesNulas(user);
      
       try{
           user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
           return repo.save(user);
       }catch(Exception e){
           throw new RuntimeException("Falha ao salvar o usuário.");
       }  
        
    }
    
    public Usuario update(Usuario user, String senhaAtual, String novaSenha, String confirmarNovaSenha){
       //verifica usuario ja existe
        Usuario obj = findById(user.getId());
        
        //verifica permissões nulas
        removePermissoesNulas(user);
        
        //Verificar alteração de senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        
        user.setSenha(obj.getSenha());
        try{
            return repo.save(user);
        }catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar usuário.");   
        }
    } 
    
    private void alterarSenha(Usuario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        if(!senhaAtual.isBlank()&& !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()){
            if(!crypt.matches(senhaAtual, obj.getSenha())){
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("Nova Senha e Confirmar Nova Senha não conferem.");
            }
            obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        }
    }
    
    public void delete(Long id){
        Usuario obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao excluir o usuário.");
        }
 
    }
    
    public void removePermissoesNulas(Usuario user) {
        user.getPermissoes().removeIf((Permissao p) -> {
            return p.getId() == null;
        });
        if (user.getPermissoes().isEmpty()) {
            throw new RuntimeException("Usuário deve conter no mínimo 1 permissão!");
        }
    }
    
}