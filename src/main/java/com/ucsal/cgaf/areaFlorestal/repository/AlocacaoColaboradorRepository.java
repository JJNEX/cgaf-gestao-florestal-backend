package com.ucsal.cgaf.areaFlorestal.repository;

import com.ucsal.cgaf.areaFlorestal.entity.AlocacaoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlocacaoColaboradorRepository extends JpaRepository<AlocacaoColaborador, Long> {
    boolean existsByAreaFlorestalId(Long idArea);
    boolean existsByColaboradorId(UUID idColaborador);
    boolean existsByAreaFlorestalIdAndColaboradorIdAndDataFimIsNull(Long idArea, UUID idColaborador);
}
