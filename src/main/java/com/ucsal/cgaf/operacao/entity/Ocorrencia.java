package com.ucsal.cgaf.operacao.entity;

import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "ocorrencia")
public class Ocorrencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoOcorrencia tipo;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal longitude;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "ocorrencia_fotos",
            joinColumns = @JoinColumn(name = "ocorrencia_id")
    )
    @Column(name = "foto_base64", columnDefinition = "TEXT")
    private List<String> fotos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelUrgencia urgencia;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, unique = true)
    private String protocolo;

    @ManyToOne
    @JoinColumn(name = "area_florestal_id", nullable = false)
    private AreaFlorestal areaFlorestal;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataOcorrencia;
}
