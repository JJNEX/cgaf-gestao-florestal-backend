package com.ucsal.cgaf.areaFlorestal.entity;

import com.ucsal.cgaf.colaborador.entity.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Getter @Setter
public class AlocacaoColaborador {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_area_florestal", nullable = false)
    private AreaFlorestal areaFlorestal;

    @ManyToOne
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador colaborador;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFim;
}
