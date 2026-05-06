package com.ucsal.cgaf.colaborador.controller;

import com.ucsal.cgaf.colaborador.dto.ColaboradorRequest;
import com.ucsal.cgaf.colaborador.service.ColaboradorService;
import com.ucsal.cgaf.util.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController @RequiredArgsConstructor
@RequestMapping("/admin/colaborador")
public class ColaboradorController {

    private final ColaboradorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar(@RequestBody @Valid ColaboradorRequest request) {
        try {
            return ResponseEntity.ok(service.criar(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable UUID id) {
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

    @GetMapping("/ativo")
    public ResponseEntity<?> listarAtivos(Pageable pageable) {
        try {
            return ResponseEntity.ok(service.listarAtivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable UUID id, @RequestBody @Valid ColaboradorRequest request) {
        try {
            return ResponseEntity.ok(new DefaultResponse<>(
                    200, "Colaborador alterado com sucesso", service.alterar(id, request)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativar(@PathVariable UUID id) {
        try {
            service.inativar(id);
            return ResponseEntity.ok(new DefaultResponse<>(
                    200,
                    "Colaborador inativada com sucesso",
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

}
