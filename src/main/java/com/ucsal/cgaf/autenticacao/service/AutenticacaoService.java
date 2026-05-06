package com.ucsal.cgaf.autenticacao.service;

import com.ucsal.cgaf.autenticacao.config.JwtUtil;
import com.ucsal.cgaf.autenticacao.dto.AutenticacaoResponse;
import com.ucsal.cgaf.usuario.entity.Usuario;
import com.ucsal.cgaf.usuario.repository.UsuarioRepository;
import com.ucsal.cgaf.usuario.service.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service @RequiredArgsConstructor
public class AutenticacaoService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AutenticacaoResponse login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmailAndAtivoTrue(email)
                .orElseThrow(() -> new RuntimeException("Usuário inativo ou inválido"));
        usuario.setUltimoLogin(LocalDateTime.now());

        usuarioRepository.save(usuario);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        String token =  jwtUtil.gerarToken(userDetails);

        return new AutenticacaoResponse(
                usuario.getId(),
                usuario.getEmail(),
                token,
                usuario.getUltimoLogin(),
                usuario.getPerfil().name());
    }

    public Usuario usuarioLogado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email).orElseThrow();
    }
}
