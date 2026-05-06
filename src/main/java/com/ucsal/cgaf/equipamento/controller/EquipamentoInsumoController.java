package com.ucsal.cgaf.equipamento.controller;

import com.ucsal.cgaf.equipamento.dto.EquipamentoInsumoRequest;
import com.ucsal.cgaf.equipamento.service.EquipamentoInsumoService;
import com.ucsal.cgaf.util.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/recurso")
public class EquipamentoInsumoController {

    private final EquipamentoInsumoService service;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid EquipamentoInsumoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<?> listar(Pageable pageable) {
        try {
            return ResponseEntity.ok(service.listar(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody @Valid EquipamentoInsumoRequest request) {
        try {
            return ResponseEntity.ok(new DefaultResponse<>(
                    200,
                    "Recurso alterado com sucesso",
                    service.alterar(id, request)
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @PatchMapping("/{id}/estoque")
    public ResponseEntity<?> gerenciarEstoque(@PathVariable Long id, @RequestParam Integer quantidade) {
        try {
            return ResponseEntity.ok(new DefaultResponse<>(
                    200,
                    "Estoque atualizado com sucesso",
                    service.gerenciarEstoque(id, quantidade)
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            service.inativar(id);
            return ResponseEntity.ok(new DefaultResponse<>(
                    200,
                    "Recurso inativado com sucesso",
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
}
