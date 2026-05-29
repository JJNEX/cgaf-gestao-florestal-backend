package com.ucsal.cgaf.colaborador.service;

import com.ucsal.cgaf.areaFlorestal.repository.AlocacaoColaboradorRepository;
import com.ucsal.cgaf.colaborador.dto.ColaboradorRequest;
import com.ucsal.cgaf.colaborador.dto.ColaboradorResponse;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import com.ucsal.cgaf.colaborador.mapper.ColaboradorMapper;
import com.ucsal.cgaf.colaborador.repository.ColaboradorRepository;
import com.ucsal.cgaf.operacao.repository.InventarioRepository;
import com.ucsal.cgaf.operacao.repository.OcorrenciaRepository;
import com.ucsal.cgaf.operacao.repository.PlantioRepository;
import com.ucsal.cgaf.usuario.entity.Perfil;
import com.ucsal.cgaf.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service @RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PlantioRepository plantioRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final InventarioRepository inventarioRepository;
    private final AlocacaoColaboradorRepository alocacaoRepository;
    private final ColaboradorMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ColaboradorResponse criar(ColaboradorRequest request) {
        if(usuarioRepository.existsByEmail(request.email())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Colaborador colaborador = mapper.toEntity(request);
        colaborador.setPerfil(Perfil.USER);

        String defaultPwd = !request.senha().isEmpty() ? request.senha() : "w."+request.cpf();
        colaborador.setSenha(passwordEncoder.encode(defaultPwd));

        return mapper.toResponse(repository.save(colaborador));
    }

    public ColaboradorResponse buscarPorId(UUID id) {
        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não identificado"));
        return mapper.toResponse(colaborador);
    }


    public Page<ColaboradorResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public Page<ColaboradorResponse> listarAtivos(Pageable pageable) {
        return repository.buscarColaboresAtivos(pageable).map(mapper::toResponse);
    }

    @Transactional
    public ColaboradorResponse alterar(UUID id, ColaboradorRequest request) {
        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não identificado"));
        if (request.nome() != null && !colaborador.getNome().equalsIgnoreCase(request.nome())) {
            colaborador.setNome(request.nome());
        }
        if (request.email() != null && !colaborador.getEmail().equalsIgnoreCase(request.email())) {
            if (usuarioRepository.existsByEmail(request.email())) {
                throw new RuntimeException("E-mail já cadastrado para outro usuário.");
            }
            colaborador.setEmail(request.email());
        }
        if (request.funcao() != null && colaborador.getFuncao().equalsIgnoreCase(request.funcao())) {
            colaborador.setFuncao(request.funcao());
        }
        if (request.areaAtuacao() != null && colaborador.getAreaAtuacao().equalsIgnoreCase(request.areaAtuacao())) {
            colaborador.setAreaAtuacao(request.areaAtuacao());
        }
        if (request.contatoEmergencia() != null
                && colaborador.getContatoEmergencia().equalsIgnoreCase(request.contatoEmergencia())) {
            colaborador.setContatoEmergencia(request.contatoEmergencia());
        }
        if (request.qualificacoes() != null
                && colaborador.getQualificacoes().equalsIgnoreCase(request.qualificacoes())) {
            colaborador.setQualificacoes(request.qualificacoes());
        }
        return mapper.toResponse(repository.save(colaborador));
    }

    @Transactional
    public void inativar(UUID id) {
        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não identificado"));
        colaborador.setAtivo(false);
        repository.save(colaborador);
    }

    @Transactional
    public void excluir(UUID id) {
        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não identificado"));

        boolean temHistorico = alocacaoRepository.existsByColaboradorId(id) ||
                             plantioRepository.existsByColaboradorId(id) ||
                             ocorrenciaRepository.existsByColaboradorId(id) ||
                             inventarioRepository.existsByColaboradorId(id);

        if (temHistorico) {
            throw new RuntimeException("Colaborador não pode ser excluído pois possui histórico. Use a inativação.");
        }

        repository.delete(colaborador);
    }
}
