package com.ucsal.cgaf.especie.repository;

import com.ucsal.cgaf.especie.entity.Especie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
    Page<Especie> findAllByAtivoTrue(Pageable pageable);
    Optional<Especie> findByIdAndAtivoTrue(Long id);
    boolean existsByNomeCientificoIgnoreCaseAndAtivoTrue(String nomeCientifico);
}
