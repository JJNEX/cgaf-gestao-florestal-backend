package com.ucsal.cgaf.relatorio.service;

import com.ucsal.cgaf.areaFlorestal.repository.AreaFlorestalRepository;
import com.ucsal.cgaf.operacao.repository.OcorrenciaRepository;
import com.ucsal.cgaf.relatorio.dto.RelatorioBiomaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final AreaFlorestalRepository areaRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final com.ucsal.cgaf.especie.repository.EspecieRepository especieRepository;
    private final com.ucsal.cgaf.equipamento.repository.EquipamentoInsumoRepository recursoRepository;
    private final com.ucsal.cgaf.operacao.repository.PlantioRepository plantioRepository;
    private final com.ucsal.cgaf.operacao.repository.InventarioRepository inventarioRepository;
    private final com.ucsal.cgaf.colaborador.repository.ColaboradorRepository colaboradorRepository;

    public List<RelatorioBiomaResponse> obterConsolidadoPorBioma() {
        return areaRepository.findAll().stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal::getBiomaPredominante,
                        java.util.stream.Collectors.collectingAndThen(
                                java.util.stream.Collectors.toList(),
                                list -> new RelatorioBiomaResponse(
                                        list.get(0).getBiomaPredominante(),
                                        (long) list.size(),
                                        list.stream().mapToDouble(a -> a.getTamanhoHectares().doubleValue()).sum()
                                )
                        )
                )).values().stream().toList();
    }

    public List<com.ucsal.cgaf.relatorio.dto.FichaTecnicaEspecieResponse> obterFichasTecnicas() {
        return especieRepository.findAllByAtivoTrue(org.springframework.data.domain.Pageable.unpaged())
                .stream().map(e -> new com.ucsal.cgaf.relatorio.dto.FichaTecnicaEspecieResponse(
                        e.getNomeCientifico(),
                        e.getNomePopular(),
                        e.getConservacao(),
                        e.getExigenciasClimaticasSolo(),
                        e.getNativa()
                )).toList();
    }

    public List<com.ucsal.cgaf.relatorio.dto.AlertaEstoqueResponse> alertasEstoqueMinimo() {
        return recursoRepository.findAll().stream()
                .filter(r -> r.getAtivo() && r.getQuantidadeEstoque() <= r.getEstoqueMinimo())
                .map(r -> new com.ucsal.cgaf.relatorio.dto.AlertaEstoqueResponse(
                        r.getCodigoPatrimonial(),
                        r.getDescricao(),
                        r.getQuantidadeEstoque(),
                        r.getEstoqueMinimo(),
                        r.getUnidadeMedida(),
                        r.getResponsavelGuarda()
                )).toList();
    }

    public List<com.ucsal.cgaf.relatorio.dto.PrevisaoReposicaoResponse> previsaoReposicao() {
        return recursoRepository.findAll().stream()
                .filter(r -> r.getAtivo() && r.getVidaUtilEstimada() != null)
                .map(r -> {
                    java.time.LocalDate previsao = r.getDataAquisicao().plusDays((long) (r.getVidaUtilEstimada() * 365));
                    double restante = java.time.temporal.ChronoUnit.DAYS.between(java.time.LocalDate.now(), previsao) / 365.0;
                    return new com.ucsal.cgaf.relatorio.dto.PrevisaoReposicaoResponse(
                            r.getCodigoPatrimonial(),
                            r.getDescricao(),
                            r.getDataAquisicao(),
                            restante,
                            previsao
                    );
                }).toList();
    }

    public com.ucsal.cgaf.relatorio.dto.ProdutividadeColaboradorResponse produtividadeColaborador(java.util.UUID colaboradorId) {
        var colaborador = colaboradorRepository.findById(colaboradorId).orElseThrow();
        java.time.LocalDateTime inicioMes = java.time.LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0);
        
        long mudas = plantioRepository.findAll().stream()
                .filter(p -> p.getColaborador().getId().equals(colaboradorId) && p.getDataHora().isAfter(inicioMes))
                .mapToLong(com.ucsal.cgaf.operacao.entity.Plantio::getQuantidadeMudas)
                .sum();
                
        long vistorias = inventarioRepository.findAll().stream()
                .filter(i -> i.getColaborador().getId().equals(colaboradorId) && i.getDataVistoria().isAfter(inicioMes.toLocalDate()))
                .count();
                
        long ocorrencias = ocorrenciaRepository.findAll().stream()
                .filter(o -> o.getColaborador().getId().equals(colaboradorId) && o.getDataOcorrencia().isAfter(inicioMes))
                .count();
                
        return new com.ucsal.cgaf.relatorio.dto.ProdutividadeColaboradorResponse(
                colaborador.getNome(), mudas, vistorias, ocorrencias
        );
    }

    public Long totalAlertasUrgentes() {
        return ocorrenciaRepository.findAll().stream()
                .filter(o -> o.getUrgencia() == com.ucsal.cgaf.operacao.entity.NivelUrgencia.ALTO || 
                             o.getUrgencia() == com.ucsal.cgaf.operacao.entity.NivelUrgencia.CRITICO)
                .count();
    }
}
