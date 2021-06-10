package com.cliente.microrestaurante.controller;

import com.cliente.microrestaurante.controller.dto.TokenDto;
import com.cliente.microrestaurante.controller.form.LoginForm;
import com.cliente.microrestaurante.service.AutenticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticaService autenticaService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        TokenDto token = autenticaService.autenticar(form);
        if(token != null){
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
