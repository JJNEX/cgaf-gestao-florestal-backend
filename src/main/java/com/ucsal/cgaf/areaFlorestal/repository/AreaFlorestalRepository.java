package com.ucsal.cgaf.areaFlorestal.repository;

import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.areaFlorestal.entity.StatusArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AreaFlorestalRepository extends JpaRepository<AreaFlorestal, Long> {

    @Query("""
        SELECT a FROM AreaFlorestal a
        LEFT JOIN FETCH a.alocacoes al
        LEFT JOIN FETCH al.colaborador
        WHERE a.id = :id
    """)
    Optional<AreaFlorestal> buscarPorId(Long id);

    @Query("""
        SELECT a FROM AreaFlorestal a
        LEFT JOIN FETCH a.alocacoes al
        LEFT JOIN FETCH al.colaborador
    """)
    Page<AreaFlorestal> buscarTodos(Pageable pageable);

    @Query("""
        SELECT a FROM AreaFlorestal a
        JOIN FETCH a.alocacoes al
        JOIN FETCH al.colaborador
        WHERE al.colaborador.id = :colaboradorId
    """)
    Page<AreaFlorestal> buscarTodosComAlocacoes(UUID colaboradorId, Pageable pageable);

    @Query("""
        SELECT a FROM AreaFlorestal a
        JOIN FETCH a.alocacoes al
        JOIN FETCH al.colaborador
        WHERE a.status = :status
    """)
    Page<AreaFlorestal> buscarTodosComAlocacoesPorStatus(StatusArea status, Pageable pageable);

    @Query("""
        SELECT a FROM AreaFlorestal a
        JOIN FETCH a.alocacoes al
        JOIN FETCH al.colaborador
        WHERE a.id = :id
    """)
    Optional<AreaFlorestal> buscarComAlocacoes(Long id);
}
