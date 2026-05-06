package com.ucsal.cgaf.operacao.mapper;

import com.ucsal.cgaf.operacao.dto.InventarioRequest;
import com.ucsal.cgaf.operacao.dto.InventarioResponse;
import com.ucsal.cgaf.operacao.entity.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public Inventario toEntity(InventarioRequest request) {
        Inventario entity = new Inventario();
        entity.setNumeroParcela(request.numeroParcela());
        entity.setQuantidadeIndividuos(request.quantidadeIndividuos());
        entity.setDapMedio(request.dapMedio());
        entity.setAlturaMedia(request.alturaMedia());
        entity.setPresencaPragasDoencas(request.presencaPragasDoencas());
        entity.setEstadoGeral(request.estadoGeral());
        entity.setDataVistoria(request.dataVistoria());
        return entity;
    }

    public InventarioResponse toResponse(Inventario entity) {
        return new InventarioResponse(
                entity.getId(),
                entity.getNumeroParcela(),
                entity.getAreaFlorestal().getNome(),
                entity.getEspecie().getAtivo() ? entity.getEspecie().getNomeCientifico() : entity.getEspecie().getNomeCientifico() + " (Inativa)",
                entity.getQuantidadeIndividuos(),
                entity.getDapMedio(),
                entity.getAlturaMedia(),
                entity.getPresencaPragasDoencas(),
                entity.getEstadoGeral(),
                entity.getDataVistoria(),
                entity.getColaborador().getNome()
        );
    }
}
