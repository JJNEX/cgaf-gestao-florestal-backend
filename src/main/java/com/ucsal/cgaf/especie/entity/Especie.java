package com.ucsal.cgaf.especie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "especie")
public class Especie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_cientifico")
    private String nomeCientifico;

    @Column(nullable = false, name = "nome_popular")
    private String nomePopular;

    @Column(nullable = false)
    private String familia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PorteEspecie porte;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConservacao conservacao;

    @Column(nullable = false)
    private Integer cicloVidaAnos;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exigenciasClimaticasSolo;

    @Column(nullable = false)
    private Boolean nativa;

    @Column(nullable = false)
    private Boolean ativo = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
}
