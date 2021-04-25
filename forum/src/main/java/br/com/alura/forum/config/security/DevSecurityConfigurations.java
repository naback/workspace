package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter
{
    // Configurações de Autorização(url, quem pode acessar cada url, perfil de acesso).
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().csrf().disable();  // csrf: cross site request forjure -> tipo de ataque hacker. Porém iremos desabilitar essa segurança, pois nossa autenticação será via Token e é imune à esse ataque.
    }
}
