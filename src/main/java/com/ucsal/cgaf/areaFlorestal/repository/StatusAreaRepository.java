package com.ucsal.cgaf.areaFlorestal.repository;

import com.ucsal.cgaf.areaFlorestal.entity.StatusArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusAreaRepository extends JpaRepository<StatusArea, Integer> {
    Optional<StatusArea> findByDescricao(String descricao);
}
