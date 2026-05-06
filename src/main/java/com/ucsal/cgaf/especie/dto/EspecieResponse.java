package com.ucsal.cgaf.especie.dto;

import com.ucsal.cgaf.especie.entity.PorteEspecie;
import com.ucsal.cgaf.especie.entity.StatusConservacao;
import java.time.LocalDateTime;

public record EspecieResponse(
    Long id,
    String nomeCientifico,
    String nomePopular,
    String familia,
    PorteEspecie porte,
    StatusConservacao conservacao,
    Integer cicloVidaAnos,
    String exigenciasClimaticasSolo,
    Boolean nativa,
    Boolean ativo,
    LocalDateTime dataCriacao
) {}
