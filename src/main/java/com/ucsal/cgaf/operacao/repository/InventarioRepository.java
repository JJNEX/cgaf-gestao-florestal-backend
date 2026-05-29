package com.ucsal.cgaf.operacao.repository;

import com.ucsal.cgaf.operacao.entity.Inventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    boolean existsByAreaFlorestalId(Long areaId);
    boolean existsByColaboradorId(java.util.UUID colaboradorId);
    boolean existsByEspecieId(Long especieId);

    Page<Inventario> findAllByColaborador_Id(UUID colaboradorId, Pageable pageable);
}
