package com.ucsal.cgaf.autenticacao.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AutenticacaoResponse(
        UUID id,
        String email,
        String token,
        LocalDateTime ultimoLogin,
        String perfil
) {
}
