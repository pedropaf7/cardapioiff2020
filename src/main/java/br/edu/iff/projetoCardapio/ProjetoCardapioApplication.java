package br.edu.iff.projetoCardapio;

import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.model.Refeicao;
import br.edu.iff.projetoCardapio.model.TipoCardapioEnum;
import br.edu.iff.projetoCardapio.model.TipoRefeicaoEnum;
import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.repository.CardapioRepository;
import br.edu.iff.projetoCardapio.repository.UsuarioRepository;
import java.util.Calendar;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoCardapioApplication implements CommandLineRunner{
        
        @Autowired
        private UsuarioRepository usuarioRepo;
        @Autowired
        private CardapioRepository cardapioRepo;
                
                
	public static void main(String[] args) {
		SpringApplication.run(ProjetoCardapioApplication.class, args);
	}

    @Override
    @Transactional 
    public void run(String... args) throws Exception {
      //Usuario
        Usuario user1 = new Usuario();
        user1.setNome("Pedro");
        user1.setEmail("pedropaf7@gmail.com");
        user1.setFuncao("Nutricionista");
        user1.setSenha("123456789");
        
      //Refeicão1
        Refeicao ref1 = new Refeicao();
        ref1.setKcal(2.5);
        ref1.setNome("Arroz");
        ref1.setTipo(TipoRefeicaoEnum.GUARNICAO);
    
        
     //Refeicão2
        Refeicao ref2 = new Refeicao();
        ref2.setKcal(1.5);
        ref2.setNome("Feijão");
        ref2.setTipo(TipoRefeicaoEnum.GUARNICAO);  
        
     //Refeicão3
        Refeicao ref3 = new Refeicao();
        ref3.setKcal(5.5);
        ref3.setNome("Bife");
        ref3.setTipo(TipoRefeicaoEnum.PROTEINA); 
        
        
      //Cardapio1
        Cardapio card1 = new Cardapio();
        card1.setAbertura("07h30min");
        card1.setData(Calendar.getInstance());
        card1.setEncerramento("09h30min");
        card1.setTipo(TipoCardapioEnum.MANHA);       
        card1.setRefeicoes(List.of(ref1,ref2,ref3));
        cardapioRepo.save(card1);
                
      //Cardapio2
        Cardapio card2 = new Cardapio();
        card2.setAbertura("11h30min");
        card2.setData(Calendar.getInstance());
        card2.setEncerramento("13h30min");
        card2.setTipo(TipoCardapioEnum.ALMOCO);
        card2.setRefeicoes(List.of(ref3,ref1)); 
        
        cardapioRepo.save(card2);
             
        user1.setCardapios(List.of(card1,card2));
        
        usuarioRepo.save(user1);

    }

}
