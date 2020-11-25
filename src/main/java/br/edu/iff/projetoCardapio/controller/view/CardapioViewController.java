package br.edu.iff.projetoCardapio.controller.view;

import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.model.TipoCardapioEnum;
import br.edu.iff.projetoCardapio.model.TipoRefeicaoEnum;
import br.edu.iff.projetoCardapio.service.CardapioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cardapios")
public class CardapioViewController {
    
    @Autowired
    private CardapioService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("cardapios", service.findAll());
        return "cardapios";
    }
    
    @GetMapping(path = "/cardapio")
    public String cadastro(Model model) {
        model.addAttribute("cardapio", new Cardapio());
        model.addAttribute("tiposCardapios", TipoCardapioEnum.values());
        model.addAttribute("tiposRefeicoes", TipoRefeicaoEnum.values());
        return "formCardapio";
    }
    
    @PostMapping(path = "/cardapio")
    public String save(@Valid @ModelAttribute Cardapio cardapio, BindingResult result, Model model) {
        model.addAttribute("tiposCardapios", TipoCardapioEnum.values());
        model.addAttribute("tiposRefeicoes", TipoRefeicaoEnum.values());
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCardapio";
        }
        cardapio.setId(null);
        try{
            service.save(cardapio);
            model.addAttribute("msgSucesso", "Cardápio cadastrado com sucesso!");
            model.addAttribute("cardapio", new Cardapio());
            return "formCardapio";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("Cardápio",e.getMessage()));
            return "formCardapio";           
        }
    }
    
    @GetMapping(path = "/cardapio/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cardapio", service.findById(id));
        model.addAttribute("tiposCardapios", TipoCardapioEnum.values());
        model.addAttribute("tiposRefeicoes", TipoRefeicaoEnum.values());
        return "formCardapio";
    }
    
    @PostMapping(path = "/cardapio/{id}")
    public String update(@Valid @ModelAttribute Cardapio cardapio, BindingResult result, @PathVariable("id") Long id, Model model) {
        model.addAttribute("tiposCardapios", TipoCardapioEnum.values());
        model.addAttribute("tiposRefeicoes", TipoRefeicaoEnum.values());
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCardapio";
        }
        cardapio.setId(id);
        try {
            service.update(cardapio);
            model.addAttribute("msgSucesso", "Cardápio atualizado com sucesso!");
            model.addAttribute("cardapio", cardapio);
            return "formCardapio";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Cardápio", e.getMessage()));
            return "formCardapio";
        }
    }
    
    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/cardapios";
    }
    
}
