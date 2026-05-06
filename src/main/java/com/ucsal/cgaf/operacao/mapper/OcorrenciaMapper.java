package com.ucsal.cgaf.operacao.mapper;

import com.ucsal.cgaf.operacao.dto.OcorrenciaRequest;
import com.ucsal.cgaf.operacao.dto.OcorrenciaResponse;
import com.ucsal.cgaf.operacao.entity.Ocorrencia;
import org.springframework.stereotype.Component;

@Component
public class OcorrenciaMapper {

    public Ocorrencia toEntity(OcorrenciaRequest request) {
        Ocorrencia entity = new Ocorrencia();
        entity.setTipo(request.tipo());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setFotos(request.fotos());
        entity.setUrgencia(request.urgencia());
        entity.setDescricao(request.descricao());
        return entity;
    }

    public OcorrenciaResponse toResponse(Ocorrencia entity) {
        return new OcorrenciaResponse(
                entity.getId(),
                entity.getTipo(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getFotos(),
                entity.getUrgencia(),
                entity.getDescricao(),
                entity.getProtocolo(),
                entity.getAreaFlorestal().getNome(),
                entity.getColaborador().getNome(),
                entity.getDataOcorrencia()
        );
    }
}
