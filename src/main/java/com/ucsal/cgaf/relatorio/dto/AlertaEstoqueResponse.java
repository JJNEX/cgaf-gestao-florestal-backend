package com.ucsal.cgaf.relatorio.dto;

public record AlertaEstoqueResponse(
    String codigoPatrimonial,
    String descricao,
    Integer quantidadeAtual,
    Integer estoqueMinimo,
    String unidadeMedida,
    String responsavelGuarda
) {}
