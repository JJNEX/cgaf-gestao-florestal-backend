package com.ucsal.cgaf.usuario.service;

import com.ucsal.cgaf.usuario.dto.AdministradorRequest;
import com.ucsal.cgaf.usuario.dto.UsuarioResponse;
import com.ucsal.cgaf.usuario.entity.Usuario;
import com.ucsal.cgaf.usuario.mapper.UsuarioMapper;
import com.ucsal.cgaf.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministradorService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Transactional
    public UsuarioResponse criar(AdministradorRequest request) {
        if(repository.existsByEmail(request.email())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Usuario usuario = mapper.toEntity(request);
        return mapper.toResponse(repository.save(usuario));
    }

    public UsuarioResponse buscarPorId(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não identificado"));
        return mapper.toResponse(usuario);
    }

    public Page<UsuarioResponse> listar(Pageable pageable) {
        return repository.findByPerfil(com.ucsal.cgaf.usuario.entity.Perfil.ADMIN, pageable)
                .map(mapper::toResponse);
    }
}
