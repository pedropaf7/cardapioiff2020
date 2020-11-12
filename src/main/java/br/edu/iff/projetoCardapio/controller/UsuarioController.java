package br.edu.iff.projetoCardapio.controller;

import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(service.findAll());
        
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
        
    }
    
    @PostMapping
    public ResponseEntity save (@Valid @RequestBody Usuario user){
        user.setId(null);
        service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping(path = "{/id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Usuario user){
        user.setId(id);
        service.update(user, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path = "/{id}/alterarSenha")
    public ResponseEntity alterarSenha(@PathVariable("id") Long id,
                                      @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
                                      @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
                                      @RequestParam(name = "confirmarNovaSenha", defaultValue = "", required = true) String confirmarNovaSenha){
        
        Usuario user = service.findById(id);
        service.update(user, senhaAtual, novaSenha, confirmarNovaSenha);
        return ResponseEntity.ok().build();
}        
    
    
}
