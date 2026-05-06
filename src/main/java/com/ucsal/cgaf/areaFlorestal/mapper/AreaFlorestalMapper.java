package com.ucsal.cgaf.areaFlorestal.mapper;

import com.ucsal.cgaf.areaFlorestal.dto.AlocacaoResponse;
import com.ucsal.cgaf.areaFlorestal.dto.AreaFlorestalRequest;
import com.ucsal.cgaf.areaFlorestal.dto.AreaFlorestalResponse;
import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaFlorestalMapper {
    public AreaFlorestal toEntity(AreaFlorestalRequest dto) {
        AreaFlorestal area = new AreaFlorestal();
        area.setNome(dto.nome());
        area.setLatitude(dto.latitude());
        area.setLongitude(dto.longitude());
        area.setMunicipio(dto.municipio());
        area.setEstado(dto.estado());
        area.setTamanhoHectares(dto.tamanhoHectares());
        area.setTipoFloresta(dto.tipoFloresta());
        area.setBiomaPredominante(dto.biomaPredominante());
        return area;
    }

    public AreaFlorestalResponse toResponse(AreaFlorestal entity) {
        List<AlocacaoResponse> alocacoes = null;
        if(entity.getAlocacoes() != null) {
            alocacoes = entity.getAlocacoes()
                    .stream()
                    .map(alocacao -> new AlocacaoResponse(
                            alocacao.getId(),
                            alocacao.getColaborador().getId(),
                            alocacao.getColaborador().getNome(),
                            alocacao.getDataInicio(),
                            alocacao.getDataFim()
                    ))
                    .toList();
        }

        return new AreaFlorestalResponse(
                entity.getId(),
                entity.getNome(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getMunicipio(),
                entity.getEstado(),
                entity.getTamanhoHectares(),
                entity.getTipoFloresta(),
                entity.getBiomaPredominante(),
                entity.getStatus().getDescricao(),
                alocacoes
        );
    }
}
