package com.ucsal.cgaf.colaborador.repository;

import com.ucsal.cgaf.colaborador.entity.Colaborador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, UUID> {
    @Query("""
        SELECT c FROM Usuario u
        INNER JOIN Colaborador c ON u.id = c.id
        WHERE u.perfil = 'USER' AND c.ativo = true
    """)
    Page<Colaborador> buscarColaboresAtivos(Pageable pageable);

    Page<Colaborador> findAll(Pageable pageable);
}
