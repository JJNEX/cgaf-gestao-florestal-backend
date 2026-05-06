package com.ucsal.cgaf.equipamento.repository;

import com.ucsal.cgaf.equipamento.entity.EquipamentoInsumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipamentoInsumoRepository extends JpaRepository<EquipamentoInsumo, Long> {
    Page<EquipamentoInsumo> findAllByAtivoTrue(Pageable pageable);
    Optional<EquipamentoInsumo> findByIdAndAtivoTrue(Long id);
}
