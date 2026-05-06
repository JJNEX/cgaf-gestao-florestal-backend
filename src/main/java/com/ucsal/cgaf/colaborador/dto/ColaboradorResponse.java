package com.ucsal.cgaf.colaborador.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ColaboradorResponse(
    UUID id,
    String nome,
    String email,
    Boolean ativo,
    String cpf,
    String matricula,
    String funcao,
    String areaAtuacao,
    LocalDate dataAdmissao,
    String contatoEmergencia,
    String qualificacoes,
    LocalDateTime dataCriacao,
    LocalDateTime dataAtualizaca
) {
}
