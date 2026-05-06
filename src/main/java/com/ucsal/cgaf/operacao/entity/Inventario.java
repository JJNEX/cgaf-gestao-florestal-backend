package com.ucsal.cgaf.operacao.entity;

import com.ucsal.cgaf.areaFlorestal.entity.AreaFlorestal;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import com.ucsal.cgaf.especie.entity.Especie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "inventario")
public class Inventario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "numero_parcela")
    private String numeroParcela;

    @ManyToOne
    @JoinColumn(name = "area_florestal_id", nullable = false)
    private AreaFlorestal areaFlorestal;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    @Column(nullable = false)
    private Integer quantidadeIndividuos;

    @Column(nullable = false)
    private Double dapMedio;

    @Column(nullable = false)
    private Double alturaMedia;

    @Column(nullable = false)
    private Boolean presencaPragasDoencas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVegetacao estadoGeral;

    @Column(nullable = false)
    private LocalDate dataVistoria;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;
}
