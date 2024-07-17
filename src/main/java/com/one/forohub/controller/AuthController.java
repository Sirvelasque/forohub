package com.one.forohub.controller;

import com.one.forohub.domain.usuarios.DatosAutenticacionUsuario;
import com.one.forohub.domain.usuarios.Usuario;
import com.one.forohub.infra.security.DatosJWTToken;
import com.one.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> authenticate(@RequestBody @Valid DatosAutenticacionUsuario loginData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.contrasena());
        var authenticatedUser = authManager.authenticate(authToken);
        var jwtToken = jwtTokenService.generateToken((Usuario) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }
}
