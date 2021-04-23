package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService
{
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication)
    {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do fórum da Alura") // quem é a aplicação que tá gerando esse token
                .setSubject(logado.getId().toString()) // quem é o dono do token, o cara que vai receber ele
                .setIssuedAt(hoje) // data de geração do token
                .setExpiration(dataExpiracao)  // data de expiração do token
                .signWith(SignatureAlgorithm.HS256, secret)  // criptografa gerando assinatura
                .compact();
    }

    public boolean isTokenValid(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public Long getIdUsuario(String token)
    {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }
}
