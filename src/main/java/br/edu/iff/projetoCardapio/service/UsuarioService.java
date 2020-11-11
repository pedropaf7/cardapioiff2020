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
       verificaEmailCadastrado(user.getEmail());
       try{
           return repo.save(user);
       }catch(Exception e){
           throw new RuntimeException("Falha ao salvar o usuário.");
       }  
        
    }
    
    private void verificaEmailCadastrado(String email){
        List<Usuario> result = repo.findByEmail(email);
        if(!result.isEmpty()){
            throw new RuntimeException("Email já cadastrado");
        }
    }
    
    
    public Usuario update(Usuario user, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        Usuario obj = findById(user.getId());
        //Verificar alteração de senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        
        user.setSenha(obj.getSenha());
        try{
            return repo.save(user);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar usuário.");   
        }
    } 
    
    private void alterarSenha(Usuario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        if(!senhaAtual.isBlank()&& !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()){
            if(!senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("Nova Senha e Confirmar Nova Senha não conferem.");
            }
            obj.setSenha(novaSenha);
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
    
}