package com.ucsal.cgaf.relatorio.dto;

import com.ucsal.cgaf.especie.entity.StatusConservacao;

public record FichaTecnicaEspecieResponse(
    String nomeCientifico,
    String nomePopular,
    StatusConservacao conservacao,
    String exigenciasClimaticasSolo,
    Boolean nativa
) {}
