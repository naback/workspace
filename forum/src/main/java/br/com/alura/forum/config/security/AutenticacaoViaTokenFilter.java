package br.com.alura.forum.config.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter
{
    private TokenService tokenService;

    public AutenticacaoViaTokenFilter(TokenService tokenService)
    {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        String token = recuperarToken(request);

        boolean valido = tokenService.isTokenValid(token);

        if (valido)
        {
            System.out.println("TOKEN VALIDO");
        }
        else
        {
            System.out.println("TOKEN IIIIIIIIIIIIIIIIIIIIIIIIIIINNNNNNNNNNNNNNNNNNNNNNNNVALIDO");
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");

        if ((token == null) || (token.isEmpty()) || !token.startsWith("Bearer "))
        {
            return null;
        }
        else
        {
            return token.substring(7, token.length());
        }
    }
}
