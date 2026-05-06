package com.ucsal.cgaf.areaFlorestal.dto;

import com.ucsal.cgaf.areaFlorestal.entity.BiomaPredominante;
import com.ucsal.cgaf.areaFlorestal.entity.TipoFloresta;

import java.math.BigDecimal;
import java.util.List;

public record AreaFlorestalResponse(
        Long id,
        String nome,
        BigDecimal latitude,
        BigDecimal longitude,
        String municipio,
        String estado,
        BigDecimal tamanhoHectares,
        TipoFloresta tipoFloresta,
        BiomaPredominante biomaPredominante,
        String status,
        List<AlocacaoResponse> alocacoes
) {
}
