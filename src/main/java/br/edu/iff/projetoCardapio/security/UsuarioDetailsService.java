package br.edu.iff.projetoCardapio.security;

import br.edu.iff.projetoCardapio.model.Permissao;
import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService{
    @Autowired
    private UsuarioRepository repo;
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repo.findByEmail(email);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado com esse e-mail: "+email);
        }
        return new User(usuario.getEmail(), usuario.getSenha(), getAuthorities(usuario.getPermissoes()));
    }
    
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> lista){
        List<GrantedAuthority> l = new ArrayList<>();
        for(Permissao p : lista){
            l.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));   
        }
        return l;    
    }
    
}
