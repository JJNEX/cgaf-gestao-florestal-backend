package com.ucsal.cgaf.operacao.service;

import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.areaFlorestal.repository.AreaFlorestalRepository;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import com.ucsal.cgaf.colaborador.repository.ColaboradorRepository;
import com.ucsal.cgaf.operacao.dto.OcorrenciaRequest;
import com.ucsal.cgaf.operacao.dto.OcorrenciaResponse;
import com.ucsal.cgaf.operacao.entity.Ocorrencia;
import com.ucsal.cgaf.operacao.mapper.OcorrenciaMapper;
import com.ucsal.cgaf.operacao.repository.OcorrenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository repository;
    private final AreaFlorestalRepository areaRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final OcorrenciaMapper mapper;

    @Transactional
    public OcorrenciaResponse criar(OcorrenciaRequest request) {
        AreaFlorestal area = areaRepository.findById(request.areaFlorestalId())
                .orElseThrow(() -> new EntityNotFoundException("Área Florestal não identificada."));

        Colaborador colaborador = colaboradorRepository.findById(request.colaboradorId())
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não identificado."));

        Ocorrencia entity = mapper.toEntity(request);
        entity.setAreaFlorestal(area);
        entity.setColaborador(colaborador);
        entity.setProtocolo(gerarProtocolo());

        Ocorrencia salva = repository.save(entity);
        
        // Simulação de emissão de alerta imediato (Log/Console)
        emitirAlerta(salva);

        return mapper.toResponse(salva);
    }

    public Page<OcorrenciaResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public OcorrenciaResponse buscarPorProtocolo(String protocolo) {
        return repository.findByProtocolo(protocolo)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Ocorrência não identificada."));
    }

    private synchronized String gerarProtocolo() {
        String year = String.valueOf(Year.now().getValue());
        String maxProtocolo = repository.findMaxProtocoloByYear(year);
        
        int sequence = 1;
        if (maxProtocolo != null && maxProtocolo.contains("-")) {
            sequence = Integer.parseInt(maxProtocolo.split("-")[1]) + 1;
        }
        
        return String.format("%s-%04d", year, sequence);
    }

    private void emitirAlerta(Ocorrencia ocorrencia) {
        System.out.println("⚠️ ALERTA IMEDIATO: Ocorrência do tipo " + ocorrencia.getTipo() + 
                           " registrada na área " + ocorrencia.getAreaFlorestal().getNome() + 
                           " com urgência " + ocorrencia.getUrgencia() + 
                           ". Protocolo: " + ocorrencia.getProtocolo());
    }
}
