package com.ucsal.cgaf.colaborador.entity;

import com.ucsal.cgaf.areaFlorestal.entity.AlocacaoColaborador;
import com.ucsal.cgaf.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity @Getter @Setter
@Table(name = "colaborador")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Colaborador extends Usuario {

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String funcao;

    @Column(nullable = false)
    private String areaAtuacao;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @Column(nullable = false)
    private String contatoEmergencia;

    @Column(columnDefinition = "TEXT")
    private String qualificacoes;

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlocacaoColaborador> alocacoes;
}
