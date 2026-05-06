package com.ucsal.cgaf.util;

import org.springframework.boot.SpringBootVersion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BaseController {
    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok("API CGAF SPRING "+ SpringBootVersion.getVersion());
    }
}
