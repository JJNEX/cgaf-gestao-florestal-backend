package com.ucsal.cgaf.operacao.service;

import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.areaFlorestal.repository.AreaFlorestalRepository;
import com.ucsal.cgaf.autenticacao.service.AutenticacaoService;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import com.ucsal.cgaf.colaborador.repository.ColaboradorRepository;
import com.ucsal.cgaf.especie.entity.Especie;
import com.ucsal.cgaf.especie.repository.EspecieRepository;
import com.ucsal.cgaf.operacao.dto.PlantioRequest;
import com.ucsal.cgaf.operacao.dto.PlantioResponse;
import com.ucsal.cgaf.operacao.entity.Plantio;
import com.ucsal.cgaf.operacao.mapper.PlantioMapper;
import com.ucsal.cgaf.operacao.repository.PlantioRepository;
import com.ucsal.cgaf.usuario.entity.Perfil;
import com.ucsal.cgaf.usuario.entity.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlantioService {

    private final PlantioRepository repository;
    private final AreaFlorestalRepository areaRepository;
    private final EspecieRepository especieRepository;
    private final ColaboradorRepository colaboradorRepository;

    private final AutenticacaoService autenticacaoService;

    private final PlantioMapper mapper;

    @Transactional
    public PlantioResponse criar(PlantioRequest request) {
        AreaFlorestal area = areaRepository.findById(request.areaFlorestalId())
                .orElseThrow(() -> new EntityNotFoundException("Área Florestal não identificada."));

        Especie especie = especieRepository.findById(request.especieId())
                .orElseThrow(() -> new EntityNotFoundException("Espécie não identificada."));

        Colaborador colaborador = colaboradorRepository.findById(request.colaboradorId())
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não identificado."));

        Plantio entity = mapper.toEntity(request);
        entity.setAreaFlorestal(area);
        entity.setEspecie(especie);
        entity.setColaborador(colaborador);

        return mapper.toResponse(repository.save(entity));
    }

    public Page<PlantioResponse> listar(Pageable  pageable) {
        Usuario user = autenticacaoService.usuarioLogado();
        if(user.getPerfil() == Perfil.ADMIN) return repository.findAll(pageable).map(mapper::toResponse);
        return repository.findAllByColaborador_Id(user.getId(), pageable).map(mapper::toResponse);
    }

    public PlantioResponse buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Plantio não identificada."));
    }
}
