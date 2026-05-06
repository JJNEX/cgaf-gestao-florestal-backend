package com.ucsal.cgaf.areaFlorestal.dto;

import java.time.LocalDate;

public record AlocacaoResponse(
        Long id,
        java.util.UUID idColaborador,
        String nomeColaborador,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
