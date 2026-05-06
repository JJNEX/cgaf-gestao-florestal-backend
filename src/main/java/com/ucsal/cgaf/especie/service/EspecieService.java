package com.ucsal.cgaf.especie.service;

import com.ucsal.cgaf.especie.dto.EspecieRequest;
import com.ucsal.cgaf.especie.dto.EspecieResponse;
import com.ucsal.cgaf.especie.entity.Especie;
import com.ucsal.cgaf.especie.mapper.EspecieMapper;
import com.ucsal.cgaf.especie.repository.EspecieRepository;
import com.ucsal.cgaf.operacao.repository.InventarioRepository;
import com.ucsal.cgaf.operacao.repository.PlantioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspecieService {

    private final EspecieRepository repository;
    private final PlantioRepository plantioRepository;
    private final InventarioRepository inventarioRepository;
    private final EspecieMapper mapper;

    @Transactional
    public EspecieResponse criar(EspecieRequest request) {
        if (repository.existsByNomeCientificoIgnoreCaseAndAtivoTrue(request.nomeCientifico())) {
            throw new RuntimeException("Espécie com este nome científico já cadastrada e ativa.");
        }

        Especie especie = mapper.toEntity(request);
        return mapper.toResponse(repository.save(especie));
    }

    public EspecieResponse buscarPorId(Long id) {
        Especie especie = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Espécie não identificada ou inativa."));
        return mapper.toResponse(especie);
    }

    public Page<EspecieResponse> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(mapper::toResponse);
    }

    @Transactional
    public EspecieResponse alterar(Long id, EspecieRequest request) {
        Especie especie = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Espécie não identificada ou inativa."));

        especie.setNomeCientifico(request.nomeCientifico());
        especie.setNomePopular(request.nomePopular());
        especie.setFamilia(request.familia());
        especie.setPorte(request.porte());
        especie.setConservacao(request.conservacao());
        especie.setCicloVidaAnos(request.cicloVidaAnos());
        especie.setExigenciasClimaticasSolo(request.exigenciasClimaticasSolo());
        especie.setNativa(request.nativa());

        return mapper.toResponse(repository.save(especie));
    }

    @Transactional
    public void inativar(Long id) {
        Especie especie = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Espécie não identificada."));
        especie.setAtivo(false);
        repository.save(especie);
    }

    @Transactional
    public void excluir(Long id) {
        Especie especie = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Espécie não identificada."));

        boolean temHistorico = plantioRepository.existsByEspecieId(id) ||
                             inventarioRepository.existsByEspecieId(id);

        if (temHistorico) {
            throw new RuntimeException("Espécie não pode ser excluída pois possui histórico. Use a inativação.");
        }

        repository.delete(especie);
    }
}
