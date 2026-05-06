package com.ucsal.cgaf.operacao.entity;

import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import com.ucsal.cgaf.especie.entity.Especie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "plantio")
public class Plantio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "area_florestal_id", nullable = false)
    private AreaFlorestal areaFlorestal;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    @Column(nullable = false)
    private Integer quantidadeMudas;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitudeTalhao;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal longitudeTalhao;

    private Double temperatura;
    private Double umidade;
    private Boolean chuva;

    @Column(nullable = false)
    private String metodoPlantio;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;
}
