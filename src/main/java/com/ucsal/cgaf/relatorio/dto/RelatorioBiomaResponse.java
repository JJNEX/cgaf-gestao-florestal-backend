package com.ucsal.cgaf.relatorio.dto;

import com.ucsal.cgaf.areaFlorestal.entity.BiomaPredominante;

public record RelatorioBiomaResponse(
    BiomaPredominante bioma,
    Long totalAreas,
    Double totalHectares
) {}
