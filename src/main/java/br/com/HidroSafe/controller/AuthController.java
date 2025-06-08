package br.com.HidroSafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.HidroSafe.model.Credenciais;
import br.com.HidroSafe.model.Token;
import br.com.HidroSafe.model.Usuario;
import br.com.HidroSafe.service.TokenService;


@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    AuthenticationManager authManager;

    @PostMapping("/login")
    public Token login(@RequestBody Credenciais credenciais){
        var authentication = new UsernamePasswordAuthenticationToken(credenciais.email(), credenciais.password());
        var usuario = (Usuario) authManager.authenticate(authentication).getPrincipal();

        return tokenService.createToken(usuario);
    }
}