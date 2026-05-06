package com.ucsal.cgaf.operacao.mapper;

import com.ucsal.cgaf.operacao.dto.PlantioRequest;
import com.ucsal.cgaf.operacao.dto.PlantioResponse;
import com.ucsal.cgaf.operacao.entity.Plantio;
import org.springframework.stereotype.Component;

@Component
public class PlantioMapper {

    public Plantio toEntity(PlantioRequest request) {
        Plantio entity = new Plantio();
        entity.setDataHora(request.dataHora());
        entity.setQuantidadeMudas(request.quantidadeMudas());
        entity.setLatitudeTalhao(request.latitudeTalhao());
        entity.setLongitudeTalhao(request.longitudeTalhao());
        entity.setTemperatura(request.temperatura());
        entity.setUmidade(request.umidade());
        entity.setChuva(request.chuva());
        entity.setMetodoPlantio(request.metodoPlantio());
        entity.setObservacoes(request.observacoes());
        return entity;
    }

    public PlantioResponse toResponse(Plantio entity) {
        return new PlantioResponse(
                entity.getId(),
                entity.getDataHora(),
                entity.getAreaFlorestal().getNome(),
                entity.getEspecie().getAtivo() ? entity.getEspecie().getNomeCientifico() : entity.getEspecie().getNomeCientifico() + " (Inativa)",
                entity.getQuantidadeMudas(),
                entity.getLatitudeTalhao(),
                entity.getLongitudeTalhao(),
                entity.getTemperatura(),
                entity.getUmidade(),
                entity.getChuva(),
                entity.getMetodoPlantio(),
                entity.getObservacoes(),
                entity.getColaborador().getNome()
        );
    }
}
