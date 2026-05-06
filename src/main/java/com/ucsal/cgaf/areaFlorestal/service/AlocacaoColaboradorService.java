package com.ucsal.cgaf.areaFlorestal.service;

import com.ucsal.cgaf.areaFlorestal.dto.AlocacaoRequest;
import com.ucsal.cgaf.areaFlorestal.dto.AlocacaoResponse;
import com.ucsal.cgaf.areaFlorestal.entity.AlocacaoColaborador;
import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.areaFlorestal.mapper.AlocacaoColaboradorMapper;
import com.ucsal.cgaf.areaFlorestal.repository.AlocacaoColaboradorRepository;
import com.ucsal.cgaf.areaFlorestal.repository.AreaFlorestalRepository;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import com.ucsal.cgaf.colaborador.repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class AlocacaoColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    private final AlocacaoColaboradorRepository alocacaoRepository;
    private final AreaFlorestalRepository areaRepository;

    private final AlocacaoColaboradorMapper alocacaoMapper;

    @Transactional
    public AlocacaoResponse alocar(AlocacaoRequest dto) {
        AreaFlorestal area = areaRepository.findById(dto.idAreaFlorestal())
                .orElseThrow(() -> new RuntimeException("Área Florestal não identificada"));

        if (area.getStatus().getDescricao().equalsIgnoreCase("Inativa")) {
            throw new RuntimeException("Área inativa");
        }

        Colaborador colaborador = colaboradorRepository.findById(dto.idColaborador())
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        if (alocacaoRepository
                .existsByAreaFlorestalIdAndColaboradorIdAndDataFimIsNull(dto.idAreaFlorestal(), dto.idColaborador())) {
            throw new RuntimeException("Colaborador já alocado");
        }

        AlocacaoColaborador alocacao = new AlocacaoColaborador();
        alocacao.setAreaFlorestal(area);
        alocacao.setColaborador(colaborador);
        alocacao.setDataInicio(dto.dataInicio());

        return alocacaoMapper.toResponse(alocacaoRepository.save(alocacao));
    }

    @Transactional
    public void encerrar(Long idAlocacao, LocalDate dataFim) {
        AlocacaoColaborador alocacao = alocacaoRepository.findById(idAlocacao)
                .orElseThrow(() -> new RuntimeException("Alocação não encontrada"));

        if (alocacao.getDataFim() != null) {
            throw new RuntimeException("Alocação já foi encerrada");
        }

        if (dataFim.isBefore(alocacao.getDataInicio())) {
            throw new RuntimeException("Data de encerramento não pode ser anterior à data de início");
        }

        if (dataFim.isAfter(LocalDate.now())) {
            throw new RuntimeException("Data de encerramento não pode ser futura");
        }

        alocacao.setDataFim(dataFim);
    }

}
