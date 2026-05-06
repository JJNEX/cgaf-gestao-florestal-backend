package com.ucsal.cgaf.operacao.dto;

import com.ucsal.cgaf.operacao.entity.NivelUrgencia;
import com.ucsal.cgaf.operacao.entity.TipoOcorrencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OcorrenciaRequest(
    @NotNull(message = "Tipo de ocorrência é obrigatório")
    TipoOcorrencia tipo,

    @NotNull(message = "Latitude é obrigatória")
    BigDecimal latitude,

    @NotNull(message = "Longitude é obrigatória")
    BigDecimal longitude,

    List<String> fotos,

    @NotNull(message = "Nível de urgência é obrigatório")
    NivelUrgencia urgencia,

    @NotBlank(message = "Descrição é obrigatória")
    String descricao,

    @NotNull(message = "ID da área florestal é obrigatório")
    Long areaFlorestalId,

    @NotNull(message = "ID do colaborador é obrigatório")
    UUID colaboradorId
) {}
