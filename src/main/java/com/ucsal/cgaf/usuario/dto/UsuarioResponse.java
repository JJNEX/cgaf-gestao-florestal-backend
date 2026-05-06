package com.ucsal.cgaf.usuario.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse(
    UUID id,
    String nome,
    String email,
    Boolean ativo,
    String perfil,
    LocalDateTime ultimoLogin,
    LocalDateTime dataCriacao,
    LocalDateTime dataAtualizacao
) {
}
