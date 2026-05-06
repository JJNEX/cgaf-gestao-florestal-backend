package com.ucsal.cgaf.areaFlorestal.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AlocacaoRequest(
        @NotNull(message = "O ID do colaborador é obrigatório")
        UUID idColaborador,

        @NotNull(message = "O ID da área florestal é obrigatório")
        Long idAreaFlorestal,

        @NotNull(message = "A data de início é obrigatória")
        LocalDate dataInicio
) {
}
