package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AutenticacaoService autenticacaoService;

    // Configurações de Autenticação(controle de acesso, login e tal).
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Configurações de Autorização(url, quem pode acessar cada url, perfil de acesso).
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .anyRequest().authenticated() // Para qualquer outra requisição, o cliente tem que estar autenticado.
                .and().formLogin(); // Esse método fala para o Spring gerar um formulário de autenticação padrão do Spring(de login no browser).
    }

    // Configurações de Recursos Estáticos(requisições de arquivos: css, js, imagens, etc). Não é o nosso caso, por essa aplicação ser somente back-end.
    @Override
    public void configure(WebSecurity web) throws Exception
    {
    }
}
