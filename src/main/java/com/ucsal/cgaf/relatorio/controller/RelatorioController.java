package com.ucsal.cgaf.relatorio.controller;

import com.ucsal.cgaf.relatorio.service.RelatorioService;
import com.ucsal.cgaf.util.DefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/relatorio")
public class RelatorioController {

    private final RelatorioService service;

    @GetMapping("/por-bioma")
    public ResponseEntity<?> consolidadoPorBioma() {
        return ResponseEntity.ok(service.obterConsolidadoPorBioma());
    }

    @GetMapping("/especies/fichas-tecnicas")
    public ResponseEntity<?> fichasTecnicas() {
        return ResponseEntity.ok(service.obterFichasTecnicas());
    }

    @GetMapping("/estoque/alertas")
    public ResponseEntity<?> alertasEstoque() {
        return ResponseEntity.ok(service.alertasEstoqueMinimo());
    }

    @GetMapping("/estoque/previsao")
    public ResponseEntity<?> previsaoReposicao() {
        return ResponseEntity.ok(service.previsaoReposicao());
    }

    @GetMapping("/produtividade/{colaboradorId}")
    public ResponseEntity<?> produtividade(@PathVariable java.util.UUID colaboradorId) {
        try {
            return ResponseEntity.ok(service.produtividadeColaborador(colaboradorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @GetMapping("/alertas/criticos")
    public ResponseEntity<?> totalAlertasCriticos() {
        try {
            return ResponseEntity.ok(new DefaultResponse<>(200, "Sucesso", service.totalAlertasUrgentes()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
}
