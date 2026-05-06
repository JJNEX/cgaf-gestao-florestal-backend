package com.ucsal.cgaf.usuario.mapper;

import com.ucsal.cgaf.usuario.dto.AdministradorRequest;
import com.ucsal.cgaf.usuario.dto.UsuarioResponse;
import com.ucsal.cgaf.usuario.entity.Perfil;
import com.ucsal.cgaf.usuario.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class UsuarioMapper {

    private final PasswordEncoder passwordEncoder;

    public Usuario toEntity(AdministradorRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setPerfil(Perfil.ADMIN);
        return usuario;
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getAtivo(),
            usuario.getPerfil().name(),
            usuario.getUltimoLogin(),
            usuario.getDataCriacao(),
            usuario.getDataAtualizacao()
        );
    }
}
