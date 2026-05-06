package com.ucsal.cgaf.operacao.service;

import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.areaFlorestal.repository.AreaFlorestalRepository;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import com.ucsal.cgaf.colaborador.repository.ColaboradorRepository;
import com.ucsal.cgaf.especie.entity.Especie;
import com.ucsal.cgaf.especie.repository.EspecieRepository;
import com.ucsal.cgaf.operacao.dto.InventarioRequest;
import com.ucsal.cgaf.operacao.dto.InventarioResponse;
import com.ucsal.cgaf.operacao.entity.Inventario;
import com.ucsal.cgaf.operacao.mapper.InventarioMapper;
import com.ucsal.cgaf.operacao.repository.InventarioRepository;
import com.ucsal.cgaf.usuario.entity.Perfil;
import com.ucsal.cgaf.usuario.entity.Usuario;
import com.ucsal.cgaf.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository repository;
    private final AreaFlorestalRepository areaRepository;
    private final EspecieRepository especieRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioRepository usuarioRepository;

    private final InventarioMapper mapper;

    @Transactional
    public InventarioResponse criar(InventarioRequest request) {
        AreaFlorestal area = areaRepository.findById(request.areaFlorestalId())
                .orElseThrow(() -> new EntityNotFoundException("Área Florestal não identificada."));

        Especie especie = especieRepository.findById(request.especieId())
                .orElseThrow(() -> new EntityNotFoundException("Espécie não identificada."));

        Colaborador colaborador = colaboradorRepository.findById(request.colaboradorId())
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não identificado."));

        Inventario entity = mapper.toEntity(request);
        entity.setAreaFlorestal(area);
        entity.setEspecie(especie);
        entity.setColaborador(colaborador);

        return mapper.toResponse(repository.save(entity));
    }

    public Page<InventarioResponse> listar(Pageable pageable) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = this.usuarioRepository.findByEmail(email).orElseThrow();
        if(user.getPerfil() == Perfil.ADMIN) return repository.findAll(pageable).map(mapper::toResponse);
        return repository.findAllByColaborador_Id(user.getId(), pageable).map(mapper::toResponse);
    }

    public InventarioResponse buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Inventário não identificada."));
    }
}
