package com.ucsal.cgaf.relatorio.dto;

public record ProdutividadeColaboradorResponse(
    String nomeColaborador,
    Long mudasPlantadasNoMes,
    Long vistoriasRealizadasNoMes,
    Long ocorrenciasRelatadasNoMes
) {}
