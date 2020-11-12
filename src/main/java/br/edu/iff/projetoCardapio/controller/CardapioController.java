package br.edu.iff.projetoCardapio.controller;

import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.service.CardapioService;
import java.util.Calendar;
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
@RequestMapping(path = "/apirest/cardapios")
public class CardapioController {
    
    @Autowired
    private CardapioService service;
    
    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "cardapioData") Calendar d){
        return ResponseEntity.ok(service.findAll(d)); 
    }    
    

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));

    }

    @PostMapping
    public ResponseEntity save (@Valid @RequestBody Cardapio card){
        card.setId(null);
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }
    
    
    @PutMapping(path = "{/id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Cardapio card){
        card.setId(id);
        service.update(card);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
