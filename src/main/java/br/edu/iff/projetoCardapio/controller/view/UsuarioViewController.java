
package br.edu.iff.projetoCardapio.controller.view;

import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioViewController {
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("usuarios", service.findAll());
        return "usuarios";
    }
    
    @GetMapping(path = "/usuario")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Cardapio());
        return "formUsuario";
    }
    
    @PostMapping(path = "/usuario")
    public String save(@Valid @ModelAttribute Usuario usuario, 
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha, 
            Model model) {
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formUsuario";
        }
        
        if(!usuario.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros", new ObjectError("usuario", "Campos senha e confirmar senha devem ser iguais!"));
            return "formUsuario";
        }
        
        usuario.setId(null);
        try{
            service.save(usuario);
            model.addAttribute("msgSucesso", "Usuário cadastrado com sucesso!");
            model.addAttribute("usuario", new Usuario());
            return "formUsuario";
        }catch (Exception e){
            model.addAttribute("msgErros", new ObjectError("usuario", e.getMessage()));
            return "formUsuario";
        }      
    } 
    
    @GetMapping(path = "/usuario/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("usuario", service.findById(id));
        return "formUsuario";
    }
    
    @PostMapping(path = "/usuario/{id}")
    public String update(@Valid @ModelAttribute Usuario usuario,
            BindingResult result,
            @PathVariable("id") Long id,
            Model model) {
        
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha")){
                list.add(fe);  
            }  
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formUsuario";
        }

        usuario.setId(id);
        try {
            service.update(usuario, "", "", "");
            model.addAttribute("msgSucesso", "Usuário atualizado com sucesso!");
            model.addAttribute("usuario", usuario);
            return "formUsuario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("usuario", e.getMessage()));
            return "formUsuario";
        }
    }
    
    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/usuarios";
    }

}
