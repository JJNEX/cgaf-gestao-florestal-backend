package com.ucsal.cgaf.areaFlorestal.controller;

import com.ucsal.cgaf.areaFlorestal.dto.AlocacaoRequest;
import com.ucsal.cgaf.areaFlorestal.service.AlocacaoColaboradorService;
import com.ucsal.cgaf.util.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/area-colaborador/alocacao")
public class AlocacaoColaboradorController {
    private final AlocacaoColaboradorService service;

    @PostMapping
    public ResponseEntity<?> alocar(@RequestBody @Valid AlocacaoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.alocar(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @PutMapping("/encerrar/{id}")
    public ResponseEntity<?> encerrar(@PathVariable Long id, @RequestParam(required = false) LocalDate dataFim) {
        try {
            service.encerrar(id, dataFim != null ? dataFim : LocalDate.now());
            return ResponseEntity.ok(new DefaultResponse<>(
                    200,
                    "Alocação encerrada com sucesso",
                    null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
}
