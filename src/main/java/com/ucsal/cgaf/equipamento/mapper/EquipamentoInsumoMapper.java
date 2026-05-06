package com.ucsal.cgaf.equipamento.mapper;

import com.ucsal.cgaf.equipamento.dto.EquipamentoInsumoRequest;
import com.ucsal.cgaf.equipamento.dto.EquipamentoInsumoResponse;
import com.ucsal.cgaf.equipamento.entity.EquipamentoInsumo;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoInsumoMapper {

    public EquipamentoInsumo toEntity(EquipamentoInsumoRequest request) {
        EquipamentoInsumo entity = new EquipamentoInsumo();
        entity.setCodigoPatrimonial(request.codigoPatrimonial());
        entity.setDescricao(request.descricao());
        entity.setCategoria(request.categoria());
        entity.setQuantidadeEstoque(request.quantidadeEstoque());
        entity.setEstoqueMinimo(request.estoqueMinimo());
        entity.setUnidadeMedida(request.unidadeMedida());
        entity.setLocalizacaoAtual(request.localizacaoAtual());
        entity.setDataAquisicao(request.dataAquisicao());
        entity.setVidaUtilEstimada(request.vidaUtilEstimada());
        entity.setResponsavelGuarda(request.responsavelGuarda());
        entity.setAtivo(true);
        return entity;
    }

    public EquipamentoInsumoResponse toResponse(EquipamentoInsumo entity) {
        return new EquipamentoInsumoResponse(
                entity.getId(),
                entity.getCodigoPatrimonial(),
                entity.getDescricao(),
                entity.getCategoria(),
                entity.getQuantidadeEstoque(),
                entity.getEstoqueMinimo(),
                entity.getUnidadeMedida(),
                entity.getLocalizacaoAtual(),
                entity.getDataAquisicao(),
                entity.getVidaUtilEstimada(),
                entity.getResponsavelGuarda(),
                entity.getAtivo(),
                entity.getDataCriacao()
        );
    }
}
