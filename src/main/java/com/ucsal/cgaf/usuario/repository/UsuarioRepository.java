package com.ucsal.cgaf.usuario.repository;

import com.ucsal.cgaf.usuario.entity.Perfil;
import com.ucsal.cgaf.usuario.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmailAndAtivoTrue(String email);
    boolean existsByEmail(String email);
    Page<Usuario> findByPerfil(Perfil perfil, Pageable pageable);
    Optional<Usuario> findByEmail(String email);
}
