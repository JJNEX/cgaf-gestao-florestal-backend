package com.ucsal.cgaf.operacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PlantioRequest(
    @NotNull(message = "Data e hora são obrigatórias")
    LocalDateTime dataHora,

    @NotNull(message = "ID da área florestal é obrigatório")
    Long areaFlorestalId,

    @NotNull(message = "ID da espécie é obrigatório")
    Long especieId,

    @NotNull(message = "Quantidade de mudas é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    Integer quantidadeMudas,

    @NotNull(message = "Latitude do talhão é obrigatória")
    BigDecimal latitudeTalhao,

    @NotNull(message = "Longitude do talhão é obrigatória")
    BigDecimal longitudeTalhao,

    Double temperatura,
    Double umidade,
    Boolean chuva,

    @NotBlank(message = "Método de plantio é obrigatório")
    String metodoPlantio,

    String observacoes,

    @NotNull(message = "ID do colaborador é obrigatório")
    UUID colaboradorId
) {}
