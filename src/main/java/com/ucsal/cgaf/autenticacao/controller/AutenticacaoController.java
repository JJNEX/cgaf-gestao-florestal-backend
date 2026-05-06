package com.ucsal.cgaf.autenticacao.controller;

import com.ucsal.cgaf.autenticacao.dto.AutenticacaoRequest;
import com.ucsal.cgaf.autenticacao.dto.AutenticacaoResponse;
import com.ucsal.cgaf.util.DefaultResponse;
import com.ucsal.cgaf.autenticacao.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController @RequiredArgsConstructor
@RequestMapping("/auth")
public class AutenticacaoController {
    private final AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticacaoRequest request) {
        try {
            AutenticacaoResponse autenticacaoResponse = autenticacaoService.login(request.email(), request.senha());
            return ResponseEntity.ok(new DefaultResponse<>(200, "Login realizado com sucesso", autenticacaoResponse));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(new DefaultResponse<>(403, e.getMessage(), null));
        }
    }
}
