package com.ucsal.cgaf.areaFlorestal.dto;

import com.ucsal.cgaf.areaFlorestal.entity.BiomaPredominante;
import com.ucsal.cgaf.areaFlorestal.entity.TipoFloresta;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record AreaFlorestalRequest(
        @NotBlank(message = "O nome da área é obrigatório")
        String nome,

        @NotNull(message = "A latitude é obrigatória")
        @DecimalMin(value = "-90.0", message = "A latitude deve ser no mínimo -90")
        @DecimalMax(value = "90.0", message = "A latitude deve ser no máximo 90")
        BigDecimal latitude,

        @NotNull(message = "A longitude é obrigatória")
        @DecimalMin(value = "-180.0", message = "A longitude deve ser no mínimo -180")
        @DecimalMax(value = "180.0", message = "A longitude deve ser no máximo 180")
        BigDecimal longitude,

        @NotBlank(message = "O município é obrigatório")
        String municipio,

        @NotBlank(message = "O estado é obrigatório")
        @Size(min = 2, max = 2, message = "Use a sigla do estado (ex: SP, MG)")
        String estado,

        @NotNull(message = "O tamanho em hectares é obrigatório")
        @Positive(message = "O tamanho deve ser um valor positivo")
        BigDecimal tamanhoHectares,

        @NotBlank(message = "O status é obrigatório")
        String status,

        @NotNull(message = "O tipo de floresta é obrigatório")
        TipoFloresta tipoFloresta,

        @NotNull(message = "O bioma predominante é obrigatório")
        BiomaPredominante biomaPredominante
) {
}
