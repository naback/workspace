package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter
{
    // Configurações de Autenticação(controle de acesso, login e tal).
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        super.configure(auth);
    }

    // Configurações de Autorização(url, quem pode acessar cada url, perfil de acesso).
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll();

        super.configure(http);
    }

    // Configurações de Recursos Estáticos(requisições de arquivos: css, js, imagens, etc). Não é o nosso caso, por essa aplicação ser somente back-end.
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        super.configure(web);
    }
}
