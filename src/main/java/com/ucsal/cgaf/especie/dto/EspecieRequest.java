package com.ucsal.cgaf.especie.dto;

import com.ucsal.cgaf.especie.entity.PorteEspecie;
import com.ucsal.cgaf.especie.entity.StatusConservacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EspecieRequest(
    @NotBlank(message = "Nome científico é obrigatório")
    String nomeCientifico,

    @NotBlank(message = "Nome popular é obrigatório")
    String nomePopular,

    @NotBlank(message = "Família é obrigatória")
    String familia,

    @NotNull(message = "Porte é obrigatório")
    PorteEspecie porte,

    @NotNull(message = "Estado de conservação é obrigatório")
    StatusConservacao conservacao,

    @NotNull(message = "Ciclo de vida é obrigatório")
    @Min(value = 1, message = "Ciclo de vida deve ser no mínimo 1 ano")
    Integer cicloVidaAnos,

    @NotBlank(message = "Exigências de clima e solo são obrigatórias")
    String exigenciasClimaticasSolo,

    @NotNull(message = "Informação se é nativa ou exótica é obrigatória")
    Boolean nativa
) {}
