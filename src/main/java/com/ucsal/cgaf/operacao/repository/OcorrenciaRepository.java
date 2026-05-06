package com.ucsal.cgaf.operacao.repository;

import com.ucsal.cgaf.operacao.entity.Ocorrencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    
    @Query("SELECT MAX(o.protocolo) FROM Ocorrencia o WHERE o.protocolo LIKE ?1%")
    String findMaxProtocoloByYear(String year);

    Optional<Ocorrencia> findByProtocolo(String protocolo);
    boolean existsByAreaFlorestalId(Long areaId);
    boolean existsByColaboradorId(java.util.UUID colaboradorId);

    Page<Ocorrencia> findAll(Pageable pageable);
}
