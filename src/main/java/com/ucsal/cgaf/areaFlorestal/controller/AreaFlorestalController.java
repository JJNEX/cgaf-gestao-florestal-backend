package com.ucsal.cgaf.areaFlorestal.controller;

import com.ucsal.cgaf.areaFlorestal.dto.AreaFlorestalRequest;
import com.ucsal.cgaf.areaFlorestal.service.AreaFlorestalService;
import com.ucsal.cgaf.util.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor
@RequestMapping("/area-florestal")
public class AreaFlorestalController {
    private final AreaFlorestalService service;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid AreaFlorestalRequest request) {
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
    public ResponseEntity<?> listar(@RequestParam(required = false) String status, Pageable pageable) {
        try {
            if (status != null) return ResponseEntity.ok(service.listarPorStatus(status, pageable));
            return ResponseEntity.ok(service.listar(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody @Valid AreaFlorestalRequest request) {
        try {
            return ResponseEntity.ok(new DefaultResponse<>(
                    200,
                    "Área florestal alterada com sucesso",
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
                    "Área florestal inativada com sucesso",
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
}
