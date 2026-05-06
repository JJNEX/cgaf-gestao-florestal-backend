package com.ucsal.cgaf.equipamento.dto;

import com.ucsal.cgaf.equipamento.entity.CategoriaRecurso;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EquipamentoInsumoRequest(
    @NotBlank(message = "Código patrimonial é obrigatório")
    String codigoPatrimonial,

    @NotBlank(message = "Descrição é obrigatória")
    String descricao,

    @NotNull(message = "Categoria é obrigatória")
    CategoriaRecurso categoria,

    @NotNull(message = "Quantidade em estoque é obrigatória")
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    Integer quantidadeEstoque,

    @NotNull(message = "Estoque mínimo é obrigatório")
    @Min(value = 0, message = "Estoque mínimo não pode ser negativo")
    Integer estoqueMinimo,

    @NotBlank(message = "Unidade de medida é obrigatória")
    String unidadeMedida,

    @NotBlank(message = "Localização atual é obrigatória")
    String localizacaoAtual,

    @NotNull(message = "Data de aquisição é obrigatória")
    LocalDate dataAquisicao,

    @NotNull(message = "Vida útil estimada é obrigatória")
    @Min(value = 0, message = "Vida útil não pode ser negativa")
    Double vidaUtilEstimada,

    @NotBlank(message = "Responsável pela guarda é obrigatório")
    String responsavelGuarda
) {}
