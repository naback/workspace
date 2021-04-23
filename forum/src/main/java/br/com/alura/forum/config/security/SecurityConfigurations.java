package br.com.alura.forum.config.security;

import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()  // depois isso deve ficar com alguma segurança (em produção)
                .antMatchers(HttpMethod.GET, "/actuator").permitAll()  // depois isso deve ficar com alguma segurança (em produção)
                .anyRequest().authenticated() // Para qualquer outra requisição, o cliente tem que estar autenticado.
                .and().csrf().disable()  // csrf: cross site request forjure -> tipo de ataque hacker. Porém iremos desabilitar essa segurança, pois nossa autenticação será via Token e é imune à esse ataque.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Tá avisando pro Spring que quando fizermos autenticação, não é para criar sessão.
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);  // Indicando para o Spring que ele deve rodar o nosso filtro de pegar o token antes de qualquer coisa.
    }

//    Autenticação por user e login (statefull, não recomendada em REST) afinal, formulario de login tem que ficar na aplicação front-end, não no back-end
//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
//                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
//                .anyRequest().authenticated() // Para qualquer outra requisição, o cliente tem que estar autenticado.
//                .and().formLogin(); // Esse método fala para o Spring gerar um formulário de autenticação padrão do Spring(de login no browser).
//    }

    // Configurações de Recursos Estáticos(requisições de arquivos: css, js, imagens, etc). Não é o nosso caso, por essa aplicação ser somente back-end.
    @Override
    public void configure(WebSecurity web) throws Exception
    {
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }
}
