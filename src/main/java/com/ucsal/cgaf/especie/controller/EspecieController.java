package com.ucsal.cgaf.especie.controller;

import com.ucsal.cgaf.especie.dto.EspecieRequest;
import com.ucsal.cgaf.especie.service.EspecieService;
import com.ucsal.cgaf.util.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/especie")
public class EspecieController {

    private final EspecieService service;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid EspecieRequest request) {
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
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody @Valid EspecieRequest request) {
        try {
            return ResponseEntity.ok(new DefaultResponse<>(
                    200,
                    "Espécie alterada com sucesso",
                    service.alterar(id, request)
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
                    "Espécie inativada com sucesso",
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
}
