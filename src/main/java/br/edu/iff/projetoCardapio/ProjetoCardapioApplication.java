package br.edu.iff.projetoCardapio;

import br.edu.iff.projetoCardapio.model.Cardapio;
import br.edu.iff.projetoCardapio.model.Permissao;
import br.edu.iff.projetoCardapio.model.Refeicao;
import br.edu.iff.projetoCardapio.model.TipoCardapioEnum;
import br.edu.iff.projetoCardapio.model.TipoRefeicaoEnum;
import br.edu.iff.projetoCardapio.model.Usuario;
import br.edu.iff.projetoCardapio.repository.CardapioRepository;
import br.edu.iff.projetoCardapio.repository.PermissaoRepository;
import br.edu.iff.projetoCardapio.repository.UsuarioRepository;
import java.util.Calendar;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoCardapioApplication implements CommandLineRunner{
        
        @Autowired
        private UsuarioRepository usuarioRepo;
        @Autowired
        private CardapioRepository cardapioRepo;
        @Autowired
        private PermissaoRepository permissaoRepo;
                
                
	public static void main(String[] args) {
		SpringApplication.run(ProjetoCardapioApplication.class, args);
	}

    @Override
    @Transactional 
    public void run(String... args) throws Exception {
        //Permissão
        Permissao p1 = new Permissao();
        p1.setNome("ADMIN");
        Permissao p2 = new Permissao();
        p2.setNome("FUNC");
        permissaoRepo.saveAll(List.of(p1,p2));
        
        
        Calendar dataTeste = Calendar.getInstance();
        dataTeste.set(Calendar.YEAR,2020);
        dataTeste.set(Calendar.MONTH,Calendar.DECEMBER);
        dataTeste.set(Calendar.DAY_OF_MONTH,25);
        
      //Usuarios
        Usuario user1 = new Usuario();
        user1.setNome("Pedro");
        user1.setEmail("pedropaf7@gmail.com");
        user1.setFuncao("Nutricionista");
        user1.setSenha(new BCryptPasswordEncoder().encode("12345678"));
        user1.setPermissoes(List.of(p1));
        
        Usuario user2 = new Usuario();
        user2.setNome("Paulo");
        user2.setEmail("paulo@gmail.com");
        user2.setFuncao("Estagiaria");
        user2.setSenha(new BCryptPasswordEncoder().encode("87654321"));
        user2.setPermissoes(List.of(p2));
        
      //Refeicão1
        Refeicao ref1 = new Refeicao();
        ref1.setKcal(2.5);
        ref1.setNome("Arroz");
        ref1.setTipo(TipoRefeicaoEnum.GUARNIÇÃO);
    
        
     //Refeicão2
        Refeicao ref2 = new Refeicao();
        ref2.setKcal(1.5);
        ref2.setNome("Feijão");
        ref2.setTipo(TipoRefeicaoEnum.GUARNIÇÃO);  
        
     //Refeicão3
        Refeicao ref3 = new Refeicao();
        ref3.setKcal(5.5);
        ref3.setNome("Bife");
        ref3.setTipo(TipoRefeicaoEnum.PROTEÍNA); 
        
        
      //Cardapio1
        Cardapio card1 = new Cardapio();
        card1.setAbertura("07h30min");
        card1.setData_(Calendar.getInstance());
        card1.setEncerramento("09h30min");
        card1.setTipo(TipoCardapioEnum.LANCHE_DA_MANHÃ);       
        card1.setRefeicoes(List.of(ref1,ref2,ref3));
        cardapioRepo.save(card1);
                
      //Cardapio2
        Cardapio card2 = new Cardapio();
        card2.setAbertura("11h30min");
        card2.setData_(dataTeste);
        card2.setEncerramento("13h30min");
        card2.setTipo(TipoCardapioEnum.ALMOÇO);
        card2.setRefeicoes(List.of(ref3,ref1)); 
        
        cardapioRepo.save(card2);
             
        user1.setCardapios(List.of(card1,card2));
        user2.setCardapios(List.of(card2));
        
        usuarioRepo.save(user1);
        usuarioRepo.save(user2);

    }

}
