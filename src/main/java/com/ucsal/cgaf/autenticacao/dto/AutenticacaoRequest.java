package com.ucsal.cgaf.autenticacao.dto;

public record AutenticacaoRequest(
        String email,
        String senha
) {
}
