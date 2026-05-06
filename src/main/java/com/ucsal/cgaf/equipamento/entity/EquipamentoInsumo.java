package com.ucsal.cgaf.equipamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "equipamento_insumo")
public class EquipamentoInsumo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoPatrimonial;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaRecurso categoria;

    @Column(nullable = false, name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @Column(nullable = false, name = "estoque_minimo")
    private Integer estoqueMinimo = 0;

    @Column(nullable = false, name = "unidade_medida")
    private String unidadeMedida;

    @Column(nullable = false)
    private String localizacaoAtual;

    @Column(nullable = false)
    private LocalDate dataAquisicao;

    @Column(nullable = false)
    private Double vidaUtilEstimada;

    @Column(nullable = false)
    private String responsavelGuarda;

    @Column(nullable = false)
    private Boolean ativo = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
}
