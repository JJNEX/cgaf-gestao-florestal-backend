package com.ucsal.cgaf.operacao.repository;

import com.ucsal.cgaf.operacao.entity.Plantio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlantioRepository extends JpaRepository<Plantio, Long> {
    boolean existsByAreaFlorestalId(Long areaId);
    boolean existsByColaboradorId(UUID colaboradorId);
    boolean existsByEspecieId(Long especieId);
    Page<Plantio> findAll(Pageable pageable);

    Page<Plantio> findAllByColaborador_Id(UUID colaboradorId, Pageable pageable);
}
