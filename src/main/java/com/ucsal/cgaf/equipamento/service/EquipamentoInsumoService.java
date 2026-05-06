package com.ucsal.cgaf.equipamento.service;

import com.ucsal.cgaf.equipamento.dto.EquipamentoInsumoRequest;
import com.ucsal.cgaf.equipamento.dto.EquipamentoInsumoResponse;
import com.ucsal.cgaf.equipamento.entity.EquipamentoInsumo;
import com.ucsal.cgaf.equipamento.mapper.EquipamentoInsumoMapper;
import com.ucsal.cgaf.equipamento.repository.EquipamentoInsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipamentoInsumoService {

    private final EquipamentoInsumoRepository repository;
    private final EquipamentoInsumoMapper mapper;

    @Transactional
    public EquipamentoInsumoResponse criar(EquipamentoInsumoRequest request) {
        EquipamentoInsumo entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public EquipamentoInsumoResponse buscarPorId(Long id) {
        EquipamentoInsumo entity = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso não identificado ou inativo."));
        return mapper.toResponse(entity);
    }

    public Page<EquipamentoInsumoResponse> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(mapper::toResponse);
    }

    @Transactional
    public EquipamentoInsumoResponse alterar(Long id, EquipamentoInsumoRequest request) {
        EquipamentoInsumo entity = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso não identificado ou inativa."));

        entity.setCodigoPatrimonial(request.codigoPatrimonial());
        entity.setDescricao(request.descricao());
        entity.setCategoria(request.categoria());
        entity.setQuantidadeEstoque(request.quantidadeEstoque());
        entity.setUnidadeMedida(request.unidadeMedida());
        entity.setEstoqueMinimo(request.estoqueMinimo());
        entity.setLocalizacaoAtual(request.localizacaoAtual());
        entity.setDataAquisicao(request.dataAquisicao());
        entity.setVidaUtilEstimada(request.vidaUtilEstimada());
        entity.setResponsavelGuarda(request.responsavelGuarda());

        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public void inativar(Long id) {
        EquipamentoInsumo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso não identificado."));
        entity.setAtivo(false);
        repository.save(entity);
    }

    @Transactional
    public EquipamentoInsumoResponse gerenciarEstoque(Long id, Integer quantidade) {
        EquipamentoInsumo entity = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso não identificado ou inativo."));
        
        int novaQuantidade = entity.getQuantidadeEstoque() + quantidade;
        if (novaQuantidade < 0) {
            throw new RuntimeException("Estoque insuficiente para a operação.");
        }
        
        entity.setQuantidadeEstoque(novaQuantidade);
        return mapper.toResponse(repository.save(entity));
    }
}
