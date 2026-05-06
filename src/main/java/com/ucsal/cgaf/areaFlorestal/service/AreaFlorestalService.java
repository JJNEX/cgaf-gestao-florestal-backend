package com.ucsal.cgaf.areaFlorestal.service;

import com.ucsal.cgaf.areaFlorestal.dto.AreaFlorestalRequest;
import com.ucsal.cgaf.areaFlorestal.dto.AreaFlorestalResponse;
import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.areaFlorestal.entity.StatusArea;
import com.ucsal.cgaf.areaFlorestal.mapper.AreaFlorestalMapper;
import com.ucsal.cgaf.areaFlorestal.repository.AlocacaoColaboradorRepository;
import com.ucsal.cgaf.areaFlorestal.repository.AreaFlorestalRepository;
import com.ucsal.cgaf.areaFlorestal.repository.StatusAreaRepository;
import com.ucsal.cgaf.operacao.repository.InventarioRepository;
import com.ucsal.cgaf.operacao.repository.OcorrenciaRepository;
import com.ucsal.cgaf.operacao.repository.PlantioRepository;
import com.ucsal.cgaf.usuario.entity.Perfil;
import com.ucsal.cgaf.usuario.entity.Usuario;
import com.ucsal.cgaf.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AreaFlorestalService {
    private final AreaFlorestalRepository areaRepository;
    private final AlocacaoColaboradorRepository alocacaoRepository;
    private final StatusAreaRepository statusRepository;
    private final PlantioRepository plantioRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final InventarioRepository inventarioRepository;
    private final UsuarioRepository usuarioRepository;

    private final AreaFlorestalMapper areaMapper;

    @Transactional
    public AreaFlorestalResponse criar(AreaFlorestalRequest dto) {
        AreaFlorestal area = areaMapper.toEntity(dto);

        StatusArea status = buscarStatusPorDesricao(dto.status() != null ? dto.status() : "Ativa");
        area.setStatus(status);

        return areaMapper.toResponse(areaRepository.save(area));
    }

    public AreaFlorestalResponse buscarPorId(Long id) {
        AreaFlorestal area = buscarPorIdEntity(id);
        return areaMapper.toResponse(area);
    }

    public Page<AreaFlorestalResponse> listar(Pageable pageable) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = this.usuarioRepository.findByEmail(email).orElseThrow();
        if(user.getPerfil() == Perfil.ADMIN) return areaRepository.buscarTodos(pageable).map(areaMapper::toResponse);
        return areaRepository.buscarTodosComAlocacoes(user.getId(), pageable).map(areaMapper::toResponse);
    }

    public Page<AreaFlorestalResponse> listarPorStatus(String status, Pageable pageable) {
        StatusArea statusArea = buscarStatusPorDesricao(status);
        return areaRepository.buscarTodosComAlocacoesPorStatus(statusArea, pageable)
                .map(areaMapper::toResponse);
    }

    @Transactional
    public AreaFlorestalResponse alterar(Long id, AreaFlorestalRequest dto) {
        AreaFlorestal area = buscarPorIdEntity(id);

        if(dto.nome() != null && !area.getNome().equalsIgnoreCase(dto.nome())) {
            area.setNome(dto.nome());
        }
        if(dto.latitude() != null && !Objects.equals(area.getLatitude(), dto.latitude())) {
            area.setLatitude(dto.latitude());
        }
        if(dto.longitude() != null && !Objects.equals(area.getLongitude(), dto.longitude())) {
            area.setLongitude(dto.longitude());
        }
        if(dto.municipio() != null && !area.getMunicipio().equalsIgnoreCase(dto.municipio())) {
            area.setMunicipio(dto.municipio());
        }
        if(dto.estado() != null && !area.getEstado().equalsIgnoreCase(dto.estado())) {
            area.setEstado(dto.estado());
        }
        if(dto.tamanhoHectares() != null && !Objects.equals(area.getTamanhoHectares(), dto.tamanhoHectares())) {
            area.setTamanhoHectares(dto.tamanhoHectares());
        }
        if(dto.status() != null && !area.getStatus().getDescricao().equalsIgnoreCase(dto.status())) {
            StatusArea status = buscarStatusPorDesricao(dto.status());
            area.setStatus(status);
        }
        if(dto.tipoFloresta() != null && !area.getTipoFloresta().equals(dto.tipoFloresta())) {
            area.setTipoFloresta(dto.tipoFloresta());
        }
        if(dto.biomaPredominante() != null && !area.getBiomaPredominante().equals(dto.biomaPredominante())) {
            area.setBiomaPredominante(dto.biomaPredominante());
        }
        return areaMapper.toResponse(areaRepository.save(area));
    }

    @Transactional
    public void inativar(Long id) {
        AreaFlorestal area = buscarPorIdEntity(id);
        StatusArea statusInativo = buscarStatusPorDesricao("Inativa");
        area.setStatus(statusInativo);
        areaRepository.save(area);
    }

    @Transactional
    public void excluir(Long id) {
        AreaFlorestal area = buscarPorIdEntity(id);

        boolean temHistorico = alocacaoRepository.existsByAreaFlorestalId(id) ||
                             plantioRepository.existsByAreaFlorestalId(id) ||
                             ocorrenciaRepository.existsByAreaFlorestalId(id) ||
                             inventarioRepository.existsByAreaFlorestalId(id);

        if (temHistorico) {
            throw new RuntimeException("Área não pode ser excluída pois possui histórico. Use a inativação.");
        }

        areaRepository.delete(area);
    }

    private AreaFlorestal buscarPorIdEntity(Long id) {
        return areaRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Área Florestal não identificada"));
    }

    private StatusArea buscarStatusPorDesricao(String descricao) {
        return statusRepository.findByDescricao(descricao)
                .orElseThrow(() -> new RuntimeException("Status '"+descricao+"' não identificado"));
    }
}
