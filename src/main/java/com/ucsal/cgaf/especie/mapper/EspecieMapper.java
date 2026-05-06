package com.ucsal.cgaf.especie.mapper;

import com.ucsal.cgaf.especie.dto.EspecieRequest;
import com.ucsal.cgaf.especie.dto.EspecieResponse;
import com.ucsal.cgaf.especie.entity.Especie;
import org.springframework.stereotype.Component;

@Component
public class EspecieMapper {

    public Especie toEntity(EspecieRequest request) {
        Especie especie = new Especie();
        especie.setNomeCientifico(request.nomeCientifico());
        especie.setNomePopular(request.nomePopular());
        especie.setFamilia(request.familia());
        especie.setPorte(request.porte());
        especie.setConservacao(request.conservacao());
        especie.setCicloVidaAnos(request.cicloVidaAnos());
        especie.setExigenciasClimaticasSolo(request.exigenciasClimaticasSolo());
        especie.setNativa(request.nativa());
        especie.setAtivo(true);
        return especie;
    }

    public EspecieResponse toResponse(Especie entity) {
        return new EspecieResponse(
                entity.getId(),
                entity.getNomeCientifico(),
                entity.getNomePopular(),
                entity.getFamilia(),
                entity.getPorte(),
                entity.getConservacao(),
                entity.getCicloVidaAnos(),
                entity.getExigenciasClimaticasSolo(),
                entity.getNativa(),
                entity.getAtivo(),
                entity.getDataCriacao()
        );
    }
}
