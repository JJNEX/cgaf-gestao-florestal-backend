package com.ucsal.cgaf.areaFlorestal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter @Setter
public class AreaFlorestal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal longitude;

    @Column(nullable = false)
    private String municipio;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private BigDecimal tamanhoHectares;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFloresta tipoFloresta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BiomaPredominante biomaPredominante;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_status")
    private StatusArea status;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "areaFlorestal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlocacaoColaborador> alocacoes;
}
