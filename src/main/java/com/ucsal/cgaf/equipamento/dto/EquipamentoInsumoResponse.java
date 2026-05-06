package com.ucsal.cgaf.equipamento.dto;

import com.ucsal.cgaf.equipamento.entity.CategoriaRecurso;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record EquipamentoInsumoResponse(
    Long id,
    String codigoPatrimonial,
    String descricao,
    CategoriaRecurso categoria,
    Integer quantidadeEstoque,
    Integer estoqueMinimo,
    String unidadeMedida,
    String localizacaoAtual,
    LocalDate dataAquisicao,
    Double vidaUtilEstimada,
    String responsavelGuarda,
    Boolean ativo,
    LocalDateTime dataCriacao
) {}
