package com.ucsal.cgaf.operacao.dto;

import com.ucsal.cgaf.operacao.entity.EstadoVegetacao;
import java.time.LocalDate;

public record InventarioResponse(
    Long id,
    String numeroParcela,
    String nomeAreaFlorestal,
    String nomeEspecie,
    Integer quantidadeIndividuos,
    Double dapMedio,
    Double alturaMedia,
    Boolean presencaPragasDoencas,
    EstadoVegetacao estadoGeral,
    LocalDate dataVistoria,
    String nomeColaborador
) {}
