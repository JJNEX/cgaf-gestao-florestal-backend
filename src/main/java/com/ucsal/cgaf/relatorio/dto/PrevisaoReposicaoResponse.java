package com.ucsal.cgaf.relatorio.dto;

import java.time.LocalDate;

public record PrevisaoReposicaoResponse(
    String codigoPatrimonial,
    String descricao,
    LocalDate dataAquisicao,
    Double vidaUtilRestanteAnos,
    LocalDate previsaoReposicao
) {}
