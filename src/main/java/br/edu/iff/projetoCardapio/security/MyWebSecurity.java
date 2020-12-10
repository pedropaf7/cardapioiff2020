package br.edu.iff.projetoCardapio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    private UsuarioDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/apirest/**")
            .and()
            .authorizeRequests()
            .antMatchers("/apirest/**").hasRole("ADMIN")
            .antMatchers("/usuarios/meusdados/**").hasAnyRole("ADMIN", "FUNC")
            .antMatchers("/usuarios").hasRole("ADMIN")
            .antMatchers("/usuarios/**").hasRole("ADMIN")
            .antMatchers("/**").hasAnyRole("ADMIN", "FUNC")
            .antMatchers("/cardapios/cardapio/{id}/deletar").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .formLogin()
            .usernameParameter("email");
                
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
    
}
