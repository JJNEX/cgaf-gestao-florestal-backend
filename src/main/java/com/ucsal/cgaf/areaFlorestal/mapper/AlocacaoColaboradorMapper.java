package com.ucsal.cgaf.areaFlorestal.mapper;

import com.ucsal.cgaf.areaFlorestal.dto.AlocacaoResponse;
import com.ucsal.cgaf.areaFlorestal.entity.AlocacaoColaborador;
import org.springframework.stereotype.Component;

@Component
public class AlocacaoColaboradorMapper {
    public AlocacaoResponse toResponse(AlocacaoColaborador entity) {
        return new AlocacaoResponse(
          entity.getId(),
          entity.getColaborador().getId(),
          entity.getColaborador().getNome(),
          entity.getDataInicio(),
          entity.getDataFim()
        );
    }
}
