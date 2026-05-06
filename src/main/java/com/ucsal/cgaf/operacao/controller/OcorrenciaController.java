package com.ucsal.cgaf.operacao.controller;

import com.ucsal.cgaf.operacao.dto.OcorrenciaRequest;
import com.ucsal.cgaf.operacao.service.OcorrenciaService;
import com.ucsal.cgaf.util.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colaborador/ocorrencia")
public class OcorrenciaController {

    private final OcorrenciaService service;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid OcorrenciaRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
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

    @GetMapping("/{protocolo}")
    public ResponseEntity<?> buscarPorProtocolo(@PathVariable String protocolo) {
        try {
            return ResponseEntity.ok(service.buscarPorProtocolo(protocolo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null));
        }
    }
}
