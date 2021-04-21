package br.com.alura.forum.controller;

import br.com.alura.forum.config.security.TokenService;
import br.com.alura.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AutenticacaoController
{
    @Autowired
    private AuthenticationManager authManager; // Disse o instrutor que essa classe não é injetada automaticamente. Mas eu acho que isso é só na versão do Spring que ele utiliza.

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form)
    {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try
        {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            System.out.println("Token generated: " + token);

            return ResponseEntity.ok().build();
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
