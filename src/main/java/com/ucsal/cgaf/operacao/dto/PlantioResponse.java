package com.ucsal.cgaf.operacao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlantioResponse(
    Long id,
    LocalDateTime dataHora,
    String nomeAreaFlorestal,
    String nomeEspecie,
    Integer quantidadeMudas,
    BigDecimal latitudeTalhao,
    BigDecimal longitudeTalhao,
    Double temperatura,
    Double umidade,
    Boolean chuva,
    String metodoPlantio,
    String observacoes,
    String nomeColaborador
) {}
