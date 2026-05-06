package com.ucsal.cgaf.operacao.dto;

import com.ucsal.cgaf.operacao.entity.EstadoVegetacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.UUID;

public record InventarioRequest(
    @NotBlank(message = "Número da parcela é obrigatório")
    String numeroParcela,

    @NotNull(message = "ID da área florestal é obrigatório")
    Long areaFlorestalId,

    @NotNull(message = "ID da espécie é obrigatório")
    Long especieId,

    @NotNull(message = "Quantidade de indivíduos é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    Integer quantidadeIndividuos,

    @NotNull(message = "DAP médio é obrigatório")
    Double dapMedio,

    @NotNull(message = "Altura média é obrigatória")
    Double alturaMedia,

    @NotNull(message = "Presença de pragas/doenças é obrigatória")
    Boolean presencaPragasDoencas,

    @NotNull(message = "Estado geral é obrigatório")
    EstadoVegetacao estadoGeral,

    @NotNull(message = "Data da vistoria é obrigatória")
    LocalDate dataVistoria,

    @NotNull(message = "ID do colaborador é obrigatório")
    UUID colaboradorId
) {}
