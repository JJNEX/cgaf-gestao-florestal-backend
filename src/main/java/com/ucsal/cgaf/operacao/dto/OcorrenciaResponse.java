package com.ucsal.cgaf.operacao.dto;

import com.ucsal.cgaf.operacao.entity.NivelUrgencia;
import com.ucsal.cgaf.operacao.entity.TipoOcorrencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OcorrenciaResponse(
    Long id,
    TipoOcorrencia tipo,
    BigDecimal latitude,
    BigDecimal longitude,
    List<String> fotos,
    NivelUrgencia urgencia,
    String descricao,
    String protocolo,
    String nomeAreaFlorestal,
    String nomeColaborador,
    LocalDateTime dataOcorrencia
) {}
